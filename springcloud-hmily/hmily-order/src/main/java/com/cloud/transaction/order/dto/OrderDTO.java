package com.cloud.transaction.order.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO {

    private Integer id;

    /**
     * 用户账号ID
     */
    private Integer userId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 购买数量
     */
    private Integer productCount;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单状态：0=已拍下，1=拍下失败，2=未支付，3=支付成功，4=支付失败
     */
    private Byte status;

    /**
     * 总价
     */
    private Long totalMoney;

    /**
     * 优惠券id
     */
    private Integer couponReceiveId;

    /**
     * 优惠金额
     */
    private BigDecimal districtMoney;

    /**
     * 支付金额
     */
    private BigDecimal paymentMoney;

    /**
     * 扣除积分
     */
    private Integer decreaseScore;

    /**
     * 赠送积分
     */
    private Integer largessScore;

    /**
     * 删除标志，默认0不删除，1删除
     */
    private Byte deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户账号ID
     *
     * @return user_id - 用户账号ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户账号ID
     *
     * @param userId 用户账号ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取购买数量
     *
     * @return product_count - 购买数量
     */
    public Integer getProductCount() {
        return productCount;
    }

    /**
     * 设置购买数量
     *
     * @param productCount 购买数量
     */
    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    /**
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取订单状态：0=已拍下，1=拍下失败，2=未支付，3=支付成功，4=支付失败
     *
     * @return status - 订单状态：0=已拍下，1=拍下失败，2=未支付，3=支付成功，4=支付失败
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置订单状态：0=已拍下，1=拍下失败，2=未支付，3=支付成功，4=支付失败
     *
     * @param status 订单状态：0=已拍下，1=拍下失败，2=未支付，3=支付成功，4=支付失败
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取总价
     *
     * @return total_money - 总价
     */
    public Long getTotalMoney() {
        return totalMoney;
    }

    /**
     * 设置总价
     *
     * @param totalMoney 总价
     */
    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 获取优惠券id
     *
     * @return coupon_receive_id - 优惠券id
     */
    public Integer getCouponReceiveId() {
        return couponReceiveId;
    }

    /**
     * 设置优惠券id
     *
     * @param couponReceiveId 优惠券id
     */
    public void setCouponReceiveId(Integer couponReceiveId) {
        this.couponReceiveId = couponReceiveId;
    }

    /**
     * 获取优惠金额
     *
     * @return district_money - 优惠金额
     */
    public BigDecimal getDistrictMoney() {
        return districtMoney;
    }

    /**
     * 设置优惠金额
     *
     * @param districtMoney 优惠金额
     */
    public void setDistrictMoney(BigDecimal districtMoney) {
        this.districtMoney = districtMoney;
    }

    /**
     * 获取支付金额
     *
     * @return payment_money - 支付金额
     */
    public BigDecimal getPaymentMoney() {
        return paymentMoney;
    }

    /**
     * 设置支付金额
     *
     * @param paymentMoney 支付金额
     */
    public void setPaymentMoney(BigDecimal paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    /**
     * 获取扣除积分
     *
     * @return decrease_score - 扣除积分
     */
    public Integer getDecreaseScore() {
        return decreaseScore;
    }

    /**
     * 设置扣除积分
     *
     * @param decreaseScore 扣除积分
     */
    public void setDecreaseScore(Integer decreaseScore) {
        this.decreaseScore = decreaseScore;
    }

    /**
     * 获取赠送积分
     *
     * @return largess_score - 赠送积分
     */
    public Integer getLargessScore() {
        return largessScore;
    }

    /**
     * 设置赠送积分
     *
     * @param largessScore 赠送积分
     */
    public void setLargessScore(Integer largessScore) {
        this.largessScore = largessScore;
    }

    /**
     * 获取删除标志，默认0不删除，1删除
     *
     * @return deleted - 删除标志，默认0不删除，1删除
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标志，默认0不删除，1删除
     *
     * @param deleted 删除标志，默认0不删除，1删除
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}