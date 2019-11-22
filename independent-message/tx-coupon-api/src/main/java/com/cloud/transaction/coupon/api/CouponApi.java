package com.cloud.transaction.coupon.api;


import com.cloud.transaction.coupon.dto.CouponDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("tx-coupon")
@RequestMapping("/coupon")
//@Component
public interface CouponApi {

    //@RequestMapping(value = "/decrease", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/decrease")
    @Hmily
    public String decrease(@RequestBody CouponDTO obj);

}


