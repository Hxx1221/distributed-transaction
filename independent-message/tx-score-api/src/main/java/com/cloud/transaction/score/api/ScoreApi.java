package com.cloud.transaction.score.api;

import com.cloud.transaction.score.dto.ScoreDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("tx-score")
@RequestMapping("/score")
public interface ScoreApi {

    @PostMapping(value = "/decrease")
    @Hmily
    public String decrease(@RequestBody ScoreDTO obj);


}
