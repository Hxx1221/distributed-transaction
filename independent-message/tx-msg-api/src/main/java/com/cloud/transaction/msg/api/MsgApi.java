package com.cloud.transaction.msg.api;

import com.cloud.transaction.msg.dto.MsgDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("tx-msg")
@RequestMapping("/msg")
public interface MsgApi {

    @PostMapping(value = "/prepareMsg")
    public  void prepareMsg(@RequestBody MsgDTO obj);

    @PostMapping(value = "/confirmMsg")
    public  void confirmMsg(@RequestBody MsgDTO obj);

    @PostMapping(value = "/deleteMsg")
    public  void deleteMsg(@RequestBody MsgDTO obj);

}
