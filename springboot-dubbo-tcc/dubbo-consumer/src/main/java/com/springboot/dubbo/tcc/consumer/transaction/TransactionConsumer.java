package com.springboot.dubbo.tcc.consumer.transaction;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.dubbo.api.ServiceAPI;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TransactionConsumer {

    @Resource
    private ServiceAPI serviceAPI;

    @Compensable(confirmMethod = "confirmSendMessage", cancelMethod = "cancelSendMessage", asyncConfirm = true)
    public void sendMessage(String message) {
//        System.out.println("this is consumer sendMessage message="+message);
//
//        System.out.println(serviceAPI.sendMessage(message));

        // 测试业务
        serviceAPI.saveOrder("001", message, "5");

        serviceAPI.isTrueSeats(message);

        serviceAPI.isNotSold(message);

    }

    public void confirmSendMessage(String message) {
        System.out.println("this is consumer confirmSendMessage message=" + message);
//        System.out.println(serviceAPI.sendMessage(message));
    }

    public void cancelSendMessage(String message) {
        System.out.println("this is consumer cancelSendMessage message=" + message);
//        System.out.println(serviceAPI.sendMessage(message));
    }
}
