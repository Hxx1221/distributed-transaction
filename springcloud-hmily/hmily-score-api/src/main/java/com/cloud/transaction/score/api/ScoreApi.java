package com.cloud.transaction.score.api;

import com.cloud.transaction.score.dto.ScoreDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 阿甘
 * @see ://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
@FeignClient("hmily-score")
@RequestMapping("/score")
public interface ScoreApi {

    @PostMapping(value = "/decrease")
    @Hmily
    public String decrease(@RequestBody ScoreDTO obj);


}
