package com.cloud.transaction.inventory.api;

import com.cloud.transaction.inventory.dto.InventoryDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("tx-inventory")
@RequestMapping("/inventory")
public interface InventoryApi {

    /**
     * 减库存
     * @param obj
     * @return
     */
    @PostMapping(value = "/decrease")
    @Hmily
    public String decrease(@RequestBody InventoryDTO obj);

}
