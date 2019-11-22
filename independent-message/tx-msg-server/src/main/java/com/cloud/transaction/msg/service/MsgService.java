package com.cloud.transaction.msg.service;


import com.cloud.transaction.msg.dto.MsgDTO;
import com.cloud.transaction.msg.entity.Msg;
import com.cloud.transaction.msg.handle.MsgHandler;
import com.cloud.transaction.msg.mapper.MsgMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 阿甘
 * @see https://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
@Service
public class MsgService {

    @Autowired
    private MsgHandler msgHandler;

    @Autowired
    private MsgMapper msgMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgService.class);

    @Transactional
    public  void prepareMsg(MsgDTO obj){
        LOGGER.info("prepare-msg............"+obj.getMsgId());
        Msg msg=new Msg();
        BeanUtils.copyProperties(obj,msg);
        //消息状态 0=待发送 1=已完成
        msg.setStatus((byte)0);
        this.msgMapper.insertSelective(msg);
    }

    @Transactional
    public  void confirmMsg(MsgDTO obj){
        LOGGER.info("confirm-msg............"+obj.getMsgId());
        //1.把消息状态改为已完成,对应设计图的第5步骤
        Msg msg=new Msg();
        msg.setMsgId(obj.getMsgId());
        msg=this.msgMapper.selectOne(msg);

        //消息状态 0=待发送 1=已完成
        msg.setStatus((byte)1);
        this.msgMapper.updateByPrimaryKeySelective(msg);

        //2.发送消息，对应设计图的第6步骤
        this.msgHandler.send(msg.getRoutingKey(),msg.getJsonMsg());
    }

    public  void deleteMsg(MsgDTO obj){
        LOGGER.info("delete-msg............"+obj.getMsgId());
        Msg msg=new Msg();
        msg.setMsgId(obj.getMsgId());
        this.msgMapper.delete(msg);
    }

    public List<Msg> selectOverdueMsgByNotSend(int t){
        return this.msgMapper.selectOverdueMsg(0,t);
    }
    public List<Msg> selectOverdueMsgBySend(int t){
        return this.msgMapper.selectOverdueMsg(1,t);
    }
}
