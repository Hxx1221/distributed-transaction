package com.cloud.transaction.msg.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tx_msg")
public class Msg {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 消息ID,例如交易订单号
     */
    @Column(name = "msg_id")
    private String msgId;

    /**
     * 消息状态 0=待发送 1=已完成 
     */
    private Byte status;

    /**
     * 回调服务名
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 消息路由key
     */
    @Column(name = "routing_key")
    private String routingKey;

    /**
     * 消息内容
     */
    @Column(name = "json_msg")
    private String jsonMsg;

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
     * 获取消息ID,例如交易订单号
     *
     * @return msg_id - 消息ID,例如交易订单号
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 设置消息ID,例如交易订单号
     *
     * @param msgId 消息ID,例如交易订单号
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取消息状态 0=待发送 1=已完成 
     *
     * @return status - 消息状态 0=待发送 1=已完成 
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置消息状态 0=待发送 1=已完成 
     *
     * @param status 消息状态 0=待发送 1=已完成 
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取回调服务名
     *
     * @return app_name - 回调服务名
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 设置回调服务名
     *
     * @param appName 回调服务名
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 获取消息路由key
     *
     * @return routing_key - 消息路由key
     */
    public String getRoutingKey() {
        return routingKey;
    }

    /**
     * 设置消息路由key
     *
     * @param routingKey 消息路由key
     */
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    /**
     * 获取消息内容
     *
     * @return json_msg - 消息内容
     */
    public String getJsonMsg() {
        return jsonMsg;
    }

    /**
     * 设置消息内容
     *
     * @param jsonMsg 消息内容
     */
    public void setJsonMsg(String jsonMsg) {
        this.jsonMsg = jsonMsg;
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