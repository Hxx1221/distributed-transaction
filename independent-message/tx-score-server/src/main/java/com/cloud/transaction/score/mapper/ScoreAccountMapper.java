package com.cloud.transaction.score.mapper;

import com.cloud.transaction.score.entity.ScoreAccount;
import tk.mybatis.mapper.common.Mapper;

public interface ScoreAccountMapper extends Mapper<ScoreAccount> {

    public  void decrease(ScoreAccount obj);

    public  void addScore(ScoreAccount obj);

}