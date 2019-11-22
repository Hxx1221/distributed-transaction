package com.cloud.transaction.coupon.controller;

import com.cloud.transaction.coupon.api.CouponApi;
import com.cloud.transaction.coupon.dto.CouponDTO;
import com.cloud.transaction.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController implements CouponApi {

    @Autowired
    private CouponService couponService;

    @Override
    public String decrease(@RequestBody CouponDTO obj) {
        this.couponService.decrease(obj);
        return null;
    }
}
