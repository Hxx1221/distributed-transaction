package com.cloud.transaction.coupon.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tx_coupon_receive")
public class CouponReceive {
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
    @Column(name = "coupon_id")
    private Integer couponId;

    /**
     * 券额
     */
    @Column(name = "coupon_money")
    private Integer couponMoney;

    /**
     * 状态，1为已领取未使用，2为已使用，3为已过期
     */
    private Byte status;

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
     * @return coupon_id - 优惠券id
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * 设置优惠券id
     *
     * @param couponId 优惠券id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取券额
     *
     * @return coupon_money - 券额
     */
    public Integer getCouponMoney() {
        return couponMoney;
    }

    /**
     * 设置券额
     *
     * @param couponMoney 券额
     */
    public void setCouponMoney(Integer couponMoney) {
        this.couponMoney = couponMoney;
    }

    /**
     * 获取状态，1为已领取未使用，2为已使用，3为已过期
     *
     * @return status - 状态，1为已领取未使用，2为已使用，3为已过期
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态，1为已领取未使用，2为已使用，3为已过期
     *
     * @param status 状态，1为已领取未使用，2为已使用，3为已过期
     */
    public void setStatus(Byte status) {
        this.status = status;
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