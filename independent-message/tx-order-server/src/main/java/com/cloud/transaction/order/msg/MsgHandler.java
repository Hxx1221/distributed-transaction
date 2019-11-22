package com.cloud.transaction.order.msg;

import com.cloud.transaction.commons.enums.MsgEnum;
import com.cloud.transaction.msg.api.MsgApi;
import com.cloud.transaction.msg.dto.MsgDTO;
import com.cloud.transaction.order.dto.OrderDTO;
import com.cloud.transaction.order.entity.Order;
import com.cloud.transaction.order.enums.OrderStatusEnum;
import com.cloud.transaction.order.mapper.OrderMapper;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings=@QueueBinding(
        value= @Queue(value="${mq.msg.queue.name}",autoDelete="false"),
        exchange=@Exchange(value="${mq.msg.exchange}",type= ExchangeTypes.DIRECT),
        key="${mq.msg.queue.routing.key}"
)
)
public class MsgHandler {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MsgApi msgApi;

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgHandler.class);

    @RabbitHandler
    public void process(String content){
        LOGGER.info("接收到支付信息88:"+content);
        //对应设计图  第8步骤.处理本地业务
        OrderDTO orderDTO=JSON.parseObject(content, OrderDTO.class);
        Order order=new Order();
        order.setOrderNo(orderDTO.getOrderNo());
        Order obj=this.orderMapper.selectOne(order);
        obj.setStatus(OrderStatusEnum.PAY_SUCCESS.getCode());
        this.orderMapper.updateByPrimaryKeySelective(obj);

        //最后删除消息，对应设计图，第9步骤将消息直接删除
        String msgid=MsgEnum.ORDER.getCode()+"-"+orderDTO.getOrderNo();
        MsgDTO msgDTO=new MsgDTO();
        msgDTO.setMsgId(msgid);
        this.msgApi.deleteMsg(msgDTO);
    }
}
