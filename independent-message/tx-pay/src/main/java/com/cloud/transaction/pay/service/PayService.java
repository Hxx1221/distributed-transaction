package com.cloud.transaction.pay.service;





import com.cloud.transaction.commons.enums.MsgEnum;
import com.cloud.transaction.commons.utils.DatetimeUtil;
import com.cloud.transaction.msg.api.MsgApi;
import com.cloud.transaction.msg.dto.MsgDTO;
import com.cloud.transaction.order.api.OrderApi;
import com.cloud.transaction.order.dto.OrderDTO;
import com.cloud.transaction.pay.dto.PayDTO;
import com.cloud.transaction.pay.dto.ScoreDTO;
import com.cloud.transaction.pay.entity.Pay;
import com.cloud.transaction.pay.mapper.PayMapper;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @author 阿甘
 * @see https://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
@Service
public class PayService {

    @Autowired
    private OrderApi orderApi;

    @Autowired
    private MsgApi msgApi;

    @Autowired
    private PayMapper payMapper;

    @Value("${spring.application.name}")
    private String appName;

    private static final String MQ_SCORE_ROUTING_KEY = "score.routing.key";
    private static final String MQ_ORDER_ROUTING_KEY = "order.routing.key";



    private static final Logger LOGGER = LoggerFactory.getLogger(PayService.class);

    public  Pay findPayByPayNo(String payNo) {
        Pay pay = new Pay();
        pay.setPayNo(payNo);
        pay = this.payMapper.selectOne(pay);
        return pay;
    }

    public  Pay findPayByOrderNo(String orderNo) {
        Pay pay = new Pay();
        pay.setOrderNo(orderNo);
        pay = this.payMapper.selectOne(pay);
        return pay;
    }

    @Transactional
    public  String createPay(PayDTO obj){
        Pay pay=new Pay();
        BeanUtils.copyProperties(obj,pay);
        //交易单号
        Random rand = new Random();
        //随机 1000-9999的数字
        int n = rand.nextInt(8999)+1000;
        String payno= DatetimeUtil.getCurrentDatetime()+n;
        pay.setPayNo("pay-"+ payno);
        //支付完成
        pay.setStatus((byte)0);
        this.payMapper.insertSelective(pay);
        return pay.getPayNo();
    }

    public  void confirmPayHandle(String payNo){
        Pay pay=new Pay();
        pay.setPayNo(payNo);
        pay=this.payMapper.selectOne(pay);

        //1.发送"待发送"的订单消息给消息服务，对应设计图的步骤1
        this.sendOrderPrepareMsg(pay);
        //1.发送"待发送"的积分消息给消息服务，对应设计图的步骤1
        OrderDTO orderDTO=this.orderApi.findOrderNo(pay.getOrderNo());
        this.sendScorePrepareMsg(pay,orderDTO.getLargessScore());

        //3.执行本地业务，对应设计图的步骤3
        this.confirmPay(pay);

        //int i=9/0;
        //4.通知消息可以发送了，对应设计图的步骤4
        this.sendOrderConfirmMsg(pay.getOrderNo());
        //4.通知消息可以发送了，对应设计图的步骤4
        this.sendScoreConfirmMsg(pay.getOrderNo());

    }

    /**
     * 把支付订单号，改为已完成。
     * @param pay
     */
    @Transactional
    public  void confirmPay(Pay pay){
        //设置支付状态 -1：取消 0 未完成 1已完成 -2:异常
        pay.setStatus((byte)1);
        this.payMapper.updateByPrimaryKeySelective(pay);
    }

    private void sendScorePrepareMsg(Pay obj,int score){
        MsgDTO msgDTO=new MsgDTO();
        String msgid= MsgEnum.SCORE.getCode()+"-"+obj.getOrderNo();
        msgDTO.setMsgId(msgid);
        msgDTO.setAppName(this.appName);
        msgDTO.setRoutingKey(MQ_SCORE_ROUTING_KEY);
        ScoreDTO scoreDTO=new ScoreDTO();
        scoreDTO.setUserId(obj.getUserId());
        scoreDTO.setOrderNo(obj.getOrderNo());
        scoreDTO.setScore(score);
        msgDTO.setJsonMsg(JSON.toJSONString(scoreDTO));
        this.msgApi.prepareMsg(msgDTO);
    }

    private void sendScoreConfirmMsg(String orderNo){
        MsgDTO msgDTO=new MsgDTO();
        String msgid=MsgEnum.SCORE.getCode()+"-"+orderNo;
        msgDTO.setMsgId(msgid);
        this.msgApi.confirmMsg(msgDTO);
    }

    private void sendOrderPrepareMsg(Pay obj){
        MsgDTO msgDTO=new MsgDTO();
        String msgid=MsgEnum.ORDER.getCode()+"-"+obj.getOrderNo();
        msgDTO.setMsgId(msgid);
        msgDTO.setAppName(this.appName);
        msgDTO.setRoutingKey(MQ_ORDER_ROUTING_KEY);
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setUserId(obj.getUserId());
        orderDTO.setOrderNo(obj.getOrderNo());
        msgDTO.setJsonMsg(JSON.toJSONString(orderDTO));
        this.msgApi.prepareMsg(msgDTO);
    }

    private void sendOrderConfirmMsg(String orderNo){
        MsgDTO msgDTO=new MsgDTO();
        String msgid=MsgEnum.ORDER.getCode()+"-"+orderNo;
        msgDTO.setMsgId(msgid);
        this.msgApi.confirmMsg(msgDTO);
    }

}
