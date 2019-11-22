package com.cloud.transaction.msg.handle;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 阿甘
 * @see https://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
@Service
public class MsgHandler {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${mq.msg.exchange}")
    private String exchange;

    public void send(String routingkey,String jsonMsg) {
        this.rabbitTemplate.convertAndSend(this.exchange,routingkey, jsonMsg);
    }
}
