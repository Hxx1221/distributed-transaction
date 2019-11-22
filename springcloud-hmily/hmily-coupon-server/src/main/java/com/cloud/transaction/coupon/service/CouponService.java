package com.cloud.transaction.coupon.service;

import com.cloud.transaction.commons.enums.TccEnum;
import com.cloud.transaction.coupon.dto.CouponDTO;
import com.cloud.transaction.coupon.entity.CouponConsume;
import com.cloud.transaction.coupon.entity.CouponReceive;
import com.cloud.transaction.coupon.mapper.CouponConsumeMapper;
import com.cloud.transaction.coupon.mapper.CouponReceiveMapper;

import org.dromara.hmily.annotation.Hmily;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService {
    @Autowired
    private CouponReceiveMapper couponReceiveMapper;
    @Autowired
    private CouponConsumeMapper couponConsumeMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponService.class);

    @Hmily(confirmMethod = "confirmMethod",cancelMethod = "cancelMethod")
    @Transactional
    public  void decrease(CouponDTO obj){
        LOGGER.info("-------进入优惠卷的try-------");
        //第一步:检验优惠卷
        CouponReceive couponReceive=new CouponReceive();
        couponReceive.setId(obj.getCouponId());
        couponReceive=this.couponReceiveMapper.selectOne(couponReceive);
        if(couponReceive==null || couponReceive.getStatus().byteValue()!=1){
            throw new RuntimeException("未查到优惠卷");
        }

        //第二步：幂等性校验
        CouponConsume couponConsume=new CouponConsume();
        couponConsume.setOrderNo(obj.getOrderNo());
        couponConsume.setCouponReceiveId(obj.getCouponId());
        couponConsume.setDeleted((byte)0);
        if(this.couponConsumeMapper.selectOne(couponConsume)!=null){
            throw new RuntimeException("存在订单号！");
        }

        //第三步：插入消费表
        BeanUtils.copyProperties(obj,couponConsume);
        couponConsume.setTxStatus(TccEnum.TRY.getCode());
        this.couponConsumeMapper.insertSelective(couponConsume);
    }

    @Transactional
    public void confirmMethod(CouponDTO obj){
        LOGGER.info("-------进入优惠卷的confirm-------");
        //第一步：幂等性校验
        CouponConsume couponConsume=new CouponConsume();
        couponConsume.setOrderNo(obj.getOrderNo());
        couponConsume.setCouponReceiveId(obj.getCouponId());
        couponConsume=this.couponConsumeMapper.selectOne(couponConsume);
        if(couponConsume.getTxStatus()==TccEnum.CONFIRM.getCode()){
            throw new RuntimeException("该订单号，已经confirm!");
        }

        //第二步：修改订单明细状态，作用：幂等性校验
        CouponConsume updateCouponConsume=new CouponConsume();
        updateCouponConsume.setId(couponConsume.getId());
        updateCouponConsume.setTxStatus(TccEnum.CONFIRM.getCode());
        this.couponConsumeMapper.updateByPrimaryKeySelective(updateCouponConsume);
        //第三步：该领取表
        CouponReceive couponReceive=new CouponReceive();
        couponReceive.setId(obj.getCouponId());
        couponReceive.setStatus((byte)2);
        this.couponReceiveMapper.updateByPrimaryKeySelective(couponReceive);

    }
    @Transactional
    public  void cancelMethod(CouponDTO obj){
        LOGGER.info("-------进入优惠卷的cancel-------");
        //第一步：幂等性校验
        CouponConsume couponConsume=new CouponConsume();
        couponConsume.setOrderNo(obj.getOrderNo());
        couponConsume.setCouponReceiveId(obj.getCouponId());
        couponConsume=this.couponConsumeMapper.selectOne(couponConsume);
        if(couponConsume.getTxStatus()==TccEnum.CANCEL.getCode()){
            throw new RuntimeException("该订单号，已经cancel!");
        }

        //第二步：删除消费表，并且设置事务状态为cancel
        CouponConsume updateCouponConsume=new CouponConsume();
        updateCouponConsume.setId(couponConsume.getId());
        updateCouponConsume.setTxStatus(TccEnum.CANCEL.getCode());
        updateCouponConsume.setDeleted((byte)1);
        this.couponConsumeMapper.updateByPrimaryKeySelective(updateCouponConsume);
    }


}
