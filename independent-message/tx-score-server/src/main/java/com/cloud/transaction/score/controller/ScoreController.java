package com.cloud.transaction.score.controller;

import com.cloud.transaction.msg.api.MsgApi;
import com.cloud.transaction.msg.dto.MsgDTO;
import com.cloud.transaction.score.api.ScoreApi;
import com.cloud.transaction.score.dto.ScoreDTO;
import com.cloud.transaction.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 阿甘
 * @see https://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
@RestController
public class ScoreController implements ScoreApi {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private MsgApi msgApi;

    @Override
    public String  decrease(ScoreDTO obj) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++"+obj);
        this.scoreService.decrease(obj);
        return  "ok";
    }

    @RequestMapping(value = "/g",method = RequestMethod.GET)
    public void get() {
        MsgDTO msgDTO=new MsgDTO();
        msgDTO.setMsgId("111");
        this.msgApi.deleteMsg(msgDTO);

    }
}
