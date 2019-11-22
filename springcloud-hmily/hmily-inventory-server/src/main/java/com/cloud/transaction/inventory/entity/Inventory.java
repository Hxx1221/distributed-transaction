package com.cloud.transaction.inventory.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tx_inventory")
public class Inventory {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 总库存
     */
    @Column(name = "total_inventory")
    private Integer totalInventory;

    /**
     * 锁定库存
     */
    @Column(name = "lock_inventory")
    private Integer lockInventory;

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
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取总库存
     *
     * @return total_inventory - 总库存
     */
    public Integer getTotalInventory() {
        return totalInventory;
    }

    /**
     * 设置总库存
     *
     * @param totalInventory 总库存
     */
    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }

    /**
     * 获取锁定库存
     *
     * @return lock_inventory - 锁定库存
     */
    public Integer getLockInventory() {
        return lockInventory;
    }

    /**
     * 设置锁定库存
     *
     * @param lockInventory 锁定库存
     */
    public void setLockInventory(Integer lockInventory) {
        this.lockInventory = lockInventory;
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