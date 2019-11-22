package com.cloud.transaction.score.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tx_score_account")
public class ScoreAccount {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 产品ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 积分总数
     */
    @Column(name = "total_score")
    private Integer totalScore;

    /**
     * 消耗积分（锁定积分）
     */
    @Column(name = "lock_score")
    private Integer lockScore;

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
     * @return user_id - 产品ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置产品ID
     *
     * @param userId 产品ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取积分总数
     *
     * @return total_score - 积分总数
     */
    public Integer getTotalScore() {
        return totalScore;
    }

    /**
     * 设置积分总数
     *
     * @param totalScore 积分总数
     */
    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * 获取消耗积分（锁定积分）
     *
     * @return lock_score - 消耗积分（锁定积分）
     */
    public Integer getLockScore() {
        return lockScore;
    }

    /**
     * 设置消耗积分（锁定积分）
     *
     * @param lockScore 消耗积分（锁定积分）
     */
    public void setLockScore(Integer lockScore) {
        this.lockScore = lockScore;
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