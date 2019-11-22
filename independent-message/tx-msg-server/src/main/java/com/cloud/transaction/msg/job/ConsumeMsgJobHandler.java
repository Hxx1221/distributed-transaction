package com.cloud.transaction.msg.job;

import com.cloud.transaction.msg.entity.Msg;
import com.cloud.transaction.msg.handle.MsgHandler;
import com.cloud.transaction.msg.service.MsgService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
//@JobHandler(value="consumeMsgJobHandler")
//@Component
public class ConsumeMsgJobHandler extends IJobHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeMsgJobHandler.class);
	@Autowired
	private MsgHandler msgHandler;

	@Autowired
	private MsgService msgService;

	//过期时间 单位分钟
	private static final int VERDUE_TIME=1;
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		LOGGER.info("进入消费定时任务..........................");
		this.processMsg();
		return SUCCESS;
	}

	public void processMsg() {
		//1.消息服务定期轮询，过期的“已发送”消息（过期一般是根据业务规则来自行调整，例如2min）
		List<Msg> msgList = this.msgService.selectOverdueMsgBySend(VERDUE_TIME);
		for (Msg msg : msgList) {
			//2.消息服务重新将过期的“已发送”消息，发送到消息中间件MQ中。
			msgHandler.send(msg.getRoutingKey(),msg.getJsonMsg());
		}
	}
}
