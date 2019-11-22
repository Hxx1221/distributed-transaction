package com.cloud.transaction.msg.dto;


import java.util.Date;

public class MsgDTO {
    private Integer id;

    /**
     * 消息ID,例如交易订单号
     */
    private String msgId;

    /**
     * 消息状态 0=待发送 1=已完成 
     */
    private Byte status;

    /**
     * 消息路由key
     */
    private String routingKey;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    private String appName;

    /**
     * 消息内容
     */
    private String jsonMsg;

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

}