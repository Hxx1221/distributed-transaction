package com.cloud.transaction.order.service;


import com.cloud.transaction.coupon.api.CouponApi;
import com.cloud.transaction.coupon.dto.CouponDTO;
import com.cloud.transaction.inventory.api.InventoryApi;
import com.cloud.transaction.inventory.dto.InventoryDTO;
import com.cloud.transaction.order.dto.OrderDTO;
import com.cloud.transaction.order.entity.Order;
import com.cloud.transaction.order.enums.OrderStatusEnum;
import com.cloud.transaction.order.mapper.OrderMapper;
import com.cloud.transaction.score.api.ScoreApi;
import com.cloud.transaction.score.dto.ScoreDTO;
import org.dromara.hmily.annotation.Hmily;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private InventoryApi inventoryApi;
    @Autowired
    private ScoreApi scoreApi;
    @Autowired
    private CouponApi couponApi;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);


    @Transactional
    @Hmily(cancelMethod = "cancelMethod", confirmMethod = "confirmMethod")
    public void createOrder(OrderDTO obj) {
        Order order = new Order();
        BeanUtils.copyProperties(obj, order);
        order.setStatus(OrderStatusEnum.CONFIRM.getCode());
        this.orderMapper.insertSelective(order);

        //减库存
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setUserId(obj.getUserId());
        inventoryDTO.setProductId(obj.getProductId());
        inventoryDTO.setCount(obj.getProductCount());
        inventoryDTO.setOrderNo(obj.getOrderNo());
        inventoryApi.decrease(inventoryDTO);

        //减积分
        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setUserId(obj.getUserId());
        scoreDTO.setOrderNo(obj.getOrderNo());
        scoreDTO.setScore(obj.getDecreaseScore());
        this.scoreApi.decrease(scoreDTO);

        //扣优惠卷
        CouponDTO couponDTO=new CouponDTO();
        couponDTO.setUserId(obj.getUserId());
        couponDTO.setOrderNo(obj.getOrderNo());
        couponDTO.setCouponId(obj.getCouponReceiveId());
        this.couponApi.decrease(couponDTO);

    }

    @Transactional
    public void confirmMethod(OrderDTO obj) {
        LOGGER.info("-------进入订单的confirm-------");
        Order order = new Order();
        order.setOrderNo(obj.getOrderNo());
        order = orderMapper.selectOne(order);
        order.setStatus(OrderStatusEnum.NOT_PAY.getCode());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Transactional
    public void cancelMethod(OrderDTO obj) {
        LOGGER.info("-------进入订单的cancelMethod-------");
        Order order = new Order();
        order.setOrderNo(obj.getOrderNo());
        order = orderMapper.selectOne(order);
        order.setStatus(OrderStatusEnum.CONFIRM_FAIL.getCode());
        orderMapper.updateByPrimaryKeySelective(order);
    }

}
