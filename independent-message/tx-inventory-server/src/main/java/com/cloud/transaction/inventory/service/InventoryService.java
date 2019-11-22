package com.cloud.transaction.inventory.service;

import com.cloud.transaction.commons.enums.TccEnum;
import com.cloud.transaction.inventory.dto.InventoryDTO;
import com.cloud.transaction.inventory.entity.Inventory;
import com.cloud.transaction.inventory.entity.InventoryDetail;
import com.cloud.transaction.inventory.mapper.InventoryDetailMapper;
import com.cloud.transaction.inventory.mapper.InventoryMapper;
import org.dromara.hmily.annotation.Hmily;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author 阿甘
 * @see https://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
@Service
public class InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private InventoryDetailMapper inventoryDetailMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

    @Hmily(confirmMethod = "confirmMethod",cancelMethod = "cancelMethod")
    @Transactional
    public  void decrease(InventoryDTO obj){
        LOGGER.info("-------进入库存的try-------");
        //第一步:检验库存
        Inventory inventory=new Inventory();
        inventory.setProductId(obj.getProductId());
        inventory=this.inventoryMapper.selectOne(inventory);
        if(inventory.getTotalInventory()<=0 || inventory.getTotalInventory()<obj.getCount()){
            throw new RuntimeException("库存不足");
        }
        //第二步：幂等性校验
        InventoryDetail inventoryDetail=new InventoryDetail();
        inventoryDetail.setOrderNo(obj.getOrderNo());
        if(this.inventoryDetailMapper.selectOne(inventoryDetail)!=null){
            throw new RuntimeException("存在订单号！");
        }
        //第三步：插入库存明细
        BeanUtils.copyProperties(obj,inventoryDetail);
        inventoryDetail.setTxStatus(TccEnum.TRY.getCode());
        this.inventoryDetailMapper.insertSelective(inventoryDetail);

        //第四步：减库存
        Inventory updateInventory=new Inventory();
        updateInventory.setProductId(obj.getProductId());
        //库存处理
        updateInventory.setTotalInventory(-obj.getCount());
        //冻结处理
        updateInventory.setLockInventory(+obj.getCount());
        this.inventoryMapper.decrease(updateInventory);

    }

    @Transactional
    public void confirmMethod(InventoryDTO obj){
        LOGGER.info("-------进入库存的confirm-------");
        //第一步：幂等性校验
        InventoryDetail inventoryDetail=new InventoryDetail();
        inventoryDetail.setOrderNo(obj.getOrderNo());
        inventoryDetail=this.inventoryDetailMapper.selectOne(inventoryDetail);
        if(inventoryDetail.getTxStatus()==TccEnum.CONFIRM.getCode()){
            throw new RuntimeException("该订单号，已经confirm!");
        }

        //第二步：修改订单明细状态，作用：幂等性校验
        InventoryDetail updateInventoryDetail=new InventoryDetail();
        updateInventoryDetail.setId(inventoryDetail.getId());
        updateInventoryDetail.setTxStatus(TccEnum.CONFIRM.getCode());
        this.inventoryDetailMapper.updateByPrimaryKeySelective(updateInventoryDetail);

        //第三步：库存处理
        Inventory inventory=new Inventory();
        inventory.setProductId(obj.getProductId());
        //库存处理
        inventory.setTotalInventory(0);
        //冻结处理
        inventory.setLockInventory(-obj.getCount());
        this.inventoryMapper.decrease(inventory);
        //int i=9/0;
    }
    @Transactional
    public  void cancelMethod(InventoryDTO obj){
        LOGGER.info("-------进入库存的cancel-------");
        //第一步：幂等性校验
        InventoryDetail inventoryDetail=new InventoryDetail();
        inventoryDetail.setOrderNo(obj.getOrderNo());
        inventoryDetail=this.inventoryDetailMapper.selectOne(inventoryDetail);
        if(inventoryDetail.getTxStatus()==TccEnum.CANCEL.getCode()){
            throw new RuntimeException("该订单号，已经cancel!");
        }

        //第二步：修改订单明细状态，作用：幂等性校验
        InventoryDetail updateInventoryDetail=new InventoryDetail();
        updateInventoryDetail.setId(inventoryDetail.getId());
        updateInventoryDetail.setTxStatus(TccEnum.CANCEL.getCode());
        this.inventoryDetailMapper.updateByPrimaryKeySelective(updateInventoryDetail);

        //第三步：库存处理
        Inventory inventory=new Inventory();
        inventory.setProductId(obj.getProductId());
        //库存处理
        inventory.setTotalInventory(+obj.getCount());
        //冻结处理
        inventory.setLockInventory(-obj.getCount());
        this.inventoryMapper.decrease(inventory);
    }


}
