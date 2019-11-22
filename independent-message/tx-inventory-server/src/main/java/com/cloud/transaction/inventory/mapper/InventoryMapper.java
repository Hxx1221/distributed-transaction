package com.cloud.transaction.inventory.mapper;

import com.cloud.transaction.inventory.entity.Inventory;
import tk.mybatis.mapper.common.Mapper;

public interface InventoryMapper extends Mapper<Inventory> {

    public  void decrease(Inventory obj);
}