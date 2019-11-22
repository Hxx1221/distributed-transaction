package com.cloud.transaction.order.controller;

import com.cloud.transaction.order.dto.OrderDTO;
import com.cloud.transaction.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(@RequestParam("a") Integer a){
        System.out.println("-----");
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(1);
        orderDTO.setProductId(1);
        orderDTO.setPaymentMoney(new BigDecimal(10));
        orderDTO.setProductCount(1);
        orderDTO.setOrderNo(UUID.randomUUID().toString());
        orderDTO.setDecreaseScore(2);
        orderDTO.setCouponReceiveId(a);
        orderService.createOrder(orderDTO);
        return "OK";
    }
}