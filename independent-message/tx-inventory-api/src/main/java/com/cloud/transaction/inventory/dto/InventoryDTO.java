package com.cloud.transaction.inventory.dto;

import lombok.Data;

@Data
public class InventoryDTO {


    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户账号ID
     */
    private Integer userId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 数量
     */
    private Integer count;




}