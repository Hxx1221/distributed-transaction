package com.cloud.transaction.score.service;

import com.cloud.transaction.commons.enums.TccEnum;

import com.cloud.transaction.score.dto.ScoreDTO;
import com.cloud.transaction.score.entity.ScoreAccount;
import com.cloud.transaction.score.entity.ScoreDetail;
import com.cloud.transaction.score.mapper.ScoreAccountMapper;
import com.cloud.transaction.score.mapper.ScoreDetailMapper;
import org.dromara.hmily.annotation.Hmily;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author 阿甘
 * @see ://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
@Service
public class ScoreService {
    @Autowired
    private ScoreAccountMapper scoreAccountMapper;
    @Autowired
    private ScoreDetailMapper scoreDetailMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreService.class);

    @Hmily(confirmMethod = "confirmMethod",cancelMethod = "cancelMethod")
    @Transactional
    public  void decrease(ScoreDTO obj){
        LOGGER.info("-------进入积分的try-------");
        //第一步:检验积分
        ScoreAccount scoreAccount=new ScoreAccount();
        scoreAccount.setUserId(obj.getUserId());
        scoreAccount=this.scoreAccountMapper.selectOne(scoreAccount);
        if(scoreAccount.getTotalScore()<=0 || scoreAccount.getTotalScore()<obj.getScore()){
            throw new RuntimeException("该用户积分不足");
        }

        //第二步：幂等性校验
        ScoreDetail scoreDetail=new ScoreDetail();
        scoreDetail.setOrderNo(obj.getOrderNo());
        if(this.scoreDetailMapper.selectOne(scoreDetail)!=null){
            throw new RuntimeException("存在订单号！");
        }

        //第三步：插入库存明细
        BeanUtils.copyProperties(obj,scoreDetail);
        scoreDetail.setTxStatus(TccEnum.TRY.getCode());
        this.scoreDetailMapper.insertSelective(scoreDetail);


        //第四步：减积分
        ScoreAccount updateScoreAccount=new ScoreAccount();
        updateScoreAccount.setUserId(obj.getUserId());
        //积分处理
        updateScoreAccount.setTotalScore(-obj.getScore());
        //锁定积分
        updateScoreAccount.setLockScore(+obj.getScore());
        this.scoreAccountMapper.decrease(updateScoreAccount);
    }

    @Transactional
    public void confirmMethod(ScoreDTO obj){
        LOGGER.info("-------进入积分的confirm-------");
        //第一步：幂等性校验
        ScoreDetail scoreDetail=new ScoreDetail();
        scoreDetail.setOrderNo(obj.getOrderNo());
        scoreDetail=this.scoreDetailMapper.selectOne(scoreDetail);
        if(scoreDetail.getTxStatus()==TccEnum.CONFIRM.getCode()){
            throw new RuntimeException("该订单号，已经confirm!");
        }

        //第二步：修改订单明细状态，作用：幂等性校验
        ScoreDetail updateScoreDetail=new ScoreDetail();
        updateScoreDetail.setId(scoreDetail.getId());
        updateScoreDetail.setTxStatus(TccEnum.CONFIRM.getCode());
        this.scoreDetailMapper.updateByPrimaryKeySelective(updateScoreDetail);

        //第三步：积分账户处理
        ScoreAccount scoreAccount=new ScoreAccount();
        scoreAccount.setUserId(obj.getUserId());
        //积分处理
        scoreAccount.setTotalScore(0);
        //积分锁定处理
        scoreAccount.setLockScore(-obj.getScore());
        this.scoreAccountMapper.decrease(scoreAccount);
    }
    @Transactional
    public  void cancelMethod(ScoreDTO obj){
        LOGGER.info("-------进入积分的cancel-------");
        //第一步：幂等性校验
        ScoreDetail scoreDetail=new ScoreDetail();
        scoreDetail.setOrderNo(obj.getOrderNo());
        scoreDetail=this.scoreDetailMapper.selectOne(scoreDetail);
        if(scoreDetail.getTxStatus()==TccEnum.CANCEL.getCode()){
            throw new RuntimeException("该订单号，已经cancel!");
        }

        //第二步：修改订单明细状态，作用：幂等性校验
        ScoreDetail updateScoreDetail=new ScoreDetail();
        updateScoreDetail.setId(scoreDetail.getId());
        updateScoreDetail.setTxStatus(TccEnum.CANCEL.getCode());
        this.scoreDetailMapper.updateByPrimaryKeySelective(updateScoreDetail);


        //第三步：积分处理
        ScoreAccount scoreAccount=new ScoreAccount();
        scoreAccount.setUserId(obj.getUserId());
        //积分处理
        scoreAccount.setTotalScore(+obj.getScore());
        //积分锁定处理
        scoreAccount.setLockScore(-obj.getScore());
        this.scoreAccountMapper.decrease(scoreAccount);
    }


}
