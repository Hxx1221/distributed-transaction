package com.cloud.transaction.inventory.controller;

import com.cloud.transaction.inventory.api.InventoryApi;
import com.cloud.transaction.inventory.dto.InventoryDTO;
import com.cloud.transaction.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 阿甘
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
@RestController
public class InventoryController implements InventoryApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;
    /**
     * 减库存
     * @param obj
     * @return
     */
    @Override
    @PostMapping(value = "/decrease")
    public String  decrease(@RequestBody InventoryDTO obj) {
        LOGGER.info("进入库存的controller..........");
        this.inventoryService.decrease(obj);
        return  "1";
    }

}
