package com.cloud.transaction.coupon.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tx_coupon_consume")
public class CouponConsume {
    /**
     * 自动增加ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 买家ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 优惠券id
     */
    @Column(name = "coupon_receive_id")
    private Integer couponReceiveId;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 事务状态 0=try，1=confirm,2=cancel
     */
    @Column(name = "tx_status")
    private Byte txStatus;

    /**
     * 删除标志，默认0不删除，1删除
     */
    private Byte deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取自动增加ID
     *
     * @return id - 自动增加ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自动增加ID
     *
     * @param id 自动增加ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取买家ID
     *
     * @return user_id - 买家ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置买家ID
     *
     * @param userId 买家ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取事务状态 0=try，1=confirm,2=cancel
     *
     * @return tx_status - 事务状态 0=try，1=confirm,2=cancel
     */
    public Byte getTxStatus() {
        return txStatus;
    }

    /**
     * 设置事务状态 0=try，1=confirm,2=cancel
     *
     * @param txStatus 事务状态 0=try，1=confirm,2=cancel
     */
    public void setTxStatus(Byte txStatus) {
        this.txStatus = txStatus;
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