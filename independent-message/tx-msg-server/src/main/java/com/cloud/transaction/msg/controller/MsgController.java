package com.cloud.transaction.msg.controller;


import com.cloud.transaction.msg.api.MsgApi;
import com.cloud.transaction.msg.dto.MsgDTO;
import com.cloud.transaction.msg.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MsgController  implements MsgApi {
    @Autowired
    private MsgService msgService;

    /**
     * 接收"待发送"消息，把消息保持为“待发送”状态
     * @param obj
     */
    public  void prepareMsg(@RequestBody MsgDTO obj){
        this.msgService.prepareMsg(obj);
    }

    /**
     * 确认消息可以发送了，
     * 把消息状态改为“已发送”
     * @param obj
     */
    public  void confirmMsg(@RequestBody MsgDTO obj){
        this.msgService.confirmMsg(obj);
    }

    /**
     * 删除消息
     * @param obj
     */
    public  void deleteMsg(@RequestBody MsgDTO obj){
        this.msgService.deleteMsg(obj);
    }



}
