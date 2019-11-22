package com.cloud.transaction.order.api;

import com.cloud.transaction.order.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient("tx-order")
@RequestMapping("/order")
public interface OrderApi {

    @RequestMapping(value = "/t",method = RequestMethod.GET)
    public String createOrder(@RequestParam("c") Integer c);

    @RequestMapping(value = "/unpayOrderList",method = RequestMethod.GET)
    public List<OrderDTO> unpayOrderList();

    @RequestMapping(value = "/orderList",method = RequestMethod.GET)
    public List<OrderDTO> orderList();

    @RequestMapping(value = "/findOrderNo",method = RequestMethod.GET)
    public OrderDTO findOrderNo(@RequestParam("orderNo") String orderNo);

}
