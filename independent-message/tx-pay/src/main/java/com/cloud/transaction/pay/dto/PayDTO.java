package com.cloud.transaction.pay.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PayDTO {
    private Integer id;

    /**
     * 交易单号
     */
    private String payNo;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户账号ID
     */
    private Integer userId;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 支付类型 0:余额 1:微信 2:支付宝 3:xxx
     */
    private Byte payState;

    /**
     * 支付来源 wx app web wap
     */
    private String source;

    /**
     * 支付状态 -1：取消 0 未完成 1已完成 -2:异常
     */
    private Byte status;

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
     * 获取交易单号
     *
     * @return pay_no - 交易单号
     */
    public String getPayNo() {
        return payNo;
    }

    /**
     * 设置交易单号
     *
     * @param payNo 交易单号
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo;
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
     * 获取交易金额
     *
     * @return amount - 交易金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置交易金额
     *
     * @param amount 交易金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取支付类型 0:余额 1:微信 2:支付宝 3:xxx
     *
     * @return pay_state - 支付类型 0:余额 1:微信 2:支付宝 3:xxx
     */
    public Byte getPayState() {
        return payState;
    }

    /**
     * 设置支付类型 0:余额 1:微信 2:支付宝 3:xxx
     *
     * @param payState 支付类型 0:余额 1:微信 2:支付宝 3:xxx
     */
    public void setPayState(Byte payState) {
        this.payState = payState;
    }

    /**
     * 获取支付来源 wx app web wap
     *
     * @return source - 支付来源 wx app web wap
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置支付来源 wx app web wap
     *
     * @param source 支付来源 wx app web wap
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取支付状态 -1：取消 0 未完成 1已完成 -2:异常
     *
     * @return status - 支付状态 -1：取消 0 未完成 1已完成 -2:异常
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置支付状态 -1：取消 0 未完成 1已完成 -2:异常
     *
     * @param status 支付状态 -1：取消 0 未完成 1已完成 -2:异常
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