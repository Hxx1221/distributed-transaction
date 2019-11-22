package com.cloud.transaction.coupon.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tx_coupon")
public class Coupon {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 所属类型,1为满减
     */
    private Byte type;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券金额，用整数，固定值目前。
     */
    private Integer money;

    /**
     * 状态，0表示未开始，1表示进行中，2表示结束
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
     * 获取所属类型,1为满减
     *
     * @return type - 所属类型,1为满减
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置所属类型,1为满减
     *
     * @param type 所属类型,1为满减
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取优惠券名称
     *
     * @return name - 优惠券名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置优惠券名称
     *
     * @param name 优惠券名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取优惠券金额，用整数，固定值目前。
     *
     * @return money - 优惠券金额，用整数，固定值目前。
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * 设置优惠券金额，用整数，固定值目前。
     *
     * @param money 优惠券金额，用整数，固定值目前。
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 获取状态，0表示未开始，1表示进行中，2表示结束
     *
     * @return status - 状态，0表示未开始，1表示进行中，2表示结束
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态，0表示未开始，1表示进行中，2表示结束
     *
     * @param status 状态，0表示未开始，1表示进行中，2表示结束
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