package com.cloud.transaction.msg.job;

import com.cloud.transaction.msg.dto.MsgDTO;
import com.cloud.transaction.msg.entity.Msg;
import com.cloud.transaction.msg.service.MsgService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 阿甘
 * @see https://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
/**
 * 任务Handler示例（Bean模式）
 *
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 */
//@JobHandler(value="deliverMsgJobHandler")
//@Component
public class DeliverMsgJobHandler extends IJobHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliverMsgJobHandler.class);
	@Autowired
	private MsgService msgService;
	@Autowired
	private LoadBalancerClient loadBalancerClient;//ribbon 负载均衡客户端
	//过期时间 单位分钟
	private static final int VERDUE_TIME=1;
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		LOGGER.info("进入投递定时任务..........................");
		this.processMsg();
		return SUCCESS;
	}

	public void processMsg(){
		//1.定时器轮询过期“待发送”状态的消息（过期消息一般是根据业务规则自行调整，例如 2min）
		List<Msg> msgList= this.msgService.selectOverdueMsgByNotSend(VERDUE_TIME);
		for(Msg msg:msgList){
			//2.消息服务向支付服务发起状态查询，并且支付服务返回业务执行状态。
			boolean bo=this.checkMsg(msg);
			//true=已支付成功，把消息改为已发送，并发送消息
			//3.消息服务对消息进行确认：如果业务执行成功，则发送MQ消息并更改消息状态为“已发送”
			if(bo){
				MsgDTO msgDTO=new MsgDTO();
				BeanUtils.copyProperties(msg,msgDTO);
				this.msgService.confirmMsg(msgDTO);
			}else{
				//3.否则删除此条消息确保数据一致性。
				//直接删除消息
				MsgDTO msgDTO=new MsgDTO();
				msgDTO.setMsgId(msg.getMsgId());
				this.msgService.deleteMsg(msgDTO);
			}
		}
	}

	/**
	 * 消息服务向支付服务发起状态查询，并且支付服务返回业务执行状态。
	 * @param msg
	 * @return
	 */
	public boolean checkMsg(Msg msg){
		//解析订单号
		String msgid=msg.getMsgId();
		String []array=msgid.split("-");
		String id=array[1];
		//调接口
		ServiceInstance si=loadBalancerClient.choose(msg.getAppName());
		StringBuffer sb=new StringBuffer("");
		sb.append("http://");
		sb.append(si.getHost());
		sb.append(":");
		sb.append(si.getPort());
		sb.append("/checkMsg/");
		sb.append(id);
		LOGGER.info("回调服务地址：{}",sb.toString());

		RestTemplate restTemplate=new RestTemplate();

		Boolean bo = restTemplate.getForObject(sb.toString(), Boolean.class, "java");
		return bo;
	}


}
