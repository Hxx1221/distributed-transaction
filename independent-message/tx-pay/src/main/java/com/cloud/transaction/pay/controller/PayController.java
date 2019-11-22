package com.cloud.transaction.pay.controller;



import com.cloud.transaction.order.api.OrderApi;
import com.cloud.transaction.order.dto.OrderDTO;
import com.cloud.transaction.pay.dto.PayDTO;
import com.cloud.transaction.pay.entity.Pay;
import com.cloud.transaction.pay.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class PayController {
    private static final String BOOK_LIST_PATH_NAME = "orderList";
    private static final String PAY_FORM_PATH_NAME = "payForm";
    private static final String PAY_CONFIRM_FORM_PATH_NAME = "payConfirmForm";
    private static final String REDIRECT_TO_ORDER_LIST_URL = "redirect:/orderList";

    @Autowired
    private PayService payService;

    @Autowired
    private OrderApi orderApi;


    /**
     * 0.查找所有的订单信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/orderList",method = RequestMethod.GET)
    public String getOrderList(ModelMap map) {
        map.addAttribute("orderList",this.orderApi.orderList());
        return BOOK_LIST_PATH_NAME;
    }


    /**
     * 1.拉起支付选择页面
     * 目的：拉起支付选择页面，让用户选择支付方式。
     * 作用：渲染支付选择页面
     * 支付页面在payForm.html
     * @param orderNo
     * @param map
     * @return
     */
    @RequestMapping(value = "/pay/create/{orderNo}", method = RequestMethod.GET)
    public String createPayForm(@PathVariable String orderNo, ModelMap map) {
        //幂等性校验
//        先判断支付订单是否存在
//          存在
//           再判断是否已支
//             未支付:跳到支付确认页面
//             已支付:提示，已支付

//          不存在
//            直接支付
        Pay pay=this.payService.findPayByOrderNo(orderNo);
        if(pay!=null){
            //- 支付状态 -1：取消 0 未完成 1已完成 -2:异常
            if(pay.getStatus().byteValue()==0){
                //跳到支付确认页面
                return  "redirect:/pay/confirmPay/"+pay.getPayNo();
            }else if(pay.getStatus().byteValue()==1){
                throw new RuntimeException("已付款成功！！");
            }else{
                throw new RuntimeException("支付异常！！");
            }
        }
        //通过订单号，查找订单信息
        OrderDTO orderDTO=this.orderApi.findOrderNo(orderNo);
        PayDTO payDTO=new PayDTO();
        payDTO.setOrderNo(orderDTO.getOrderNo());
        payDTO.setAmount(orderDTO.getPaymentMoney());
        payDTO.setUserId(orderDTO.getUserId());

        map.addAttribute("pay", payDTO);
        return PAY_FORM_PATH_NAME;
    }

    /**
     * 2.在支付页面点击"去支付"按钮，提交表单，就进入了创建支付订单
     * 作用：创建支付订单
     *
     * @param payDTO
     * @return
     */
    @RequestMapping(value = "/pay/create", method = RequestMethod.POST)
    public String create(@ModelAttribute PayDTO payDTO){
        //幂等性校验
//        先判断支付订单是否存在
//          存在
//           再判断是否已支付
//             未支付:跳到支付确认页面
//             已支付:提示，已支付
//          不存在
//            直接支付
        Pay pay=this.payService.findPayByOrderNo(payDTO.getOrderNo());
        if(pay!=null){
            //- 支付状态 -1：取消 0 未完成 1已完成 -2:异常
            if(pay.getStatus().byteValue()==0){
                return  "redirect:/pay/confirmPay/"+pay.getPayNo();
            }else if(pay.getStatus().byteValue()==1){
                throw new RuntimeException("已付款成功！！");
            }else{
                throw new RuntimeException("支付异常！！");
            }
        }else{
            String payno=this.payService.createPay(payDTO);
            //支付订单创建,就直接拉起支付页面payConfirmForm.html,即调用了/pay/confirmPay/{payNo}接口
            return  "redirect:/pay/confirmPay/"+payno;
        }
    }


    /**
     * 3.直接订单创建成功，拉起"支付页面"
     * 该接口是一个渲染页面接口，页面是payConfirmForm.html
     * @param payNo
     * @param map
     * @return
     */
    @RequestMapping(value = "/pay/confirmPay/{payNo}", method = RequestMethod.GET)
    public String confirmPayForm(@PathVariable String payNo, ModelMap map) {
        //通过支付订单号，找出支付订单的信息
        Pay pay=this.payService.findPayByPayNo(payNo);

        PayDTO payDTO=new PayDTO();
            BeanUtils.copyProperties(pay,payDTO);
        map.addAttribute("pay", payDTO);
        return PAY_CONFIRM_FORM_PATH_NAME;
    }

    /**
     * 4.在"支付页面"点击"模拟支付"按钮后，进入分布式事务，先处理本地事务，后在处理其他事务
     * @param payNo
     * @return
     */
    @RequestMapping(value = "/confirmPay",method = RequestMethod.POST)
    public String confirmPay(@RequestParam("payNo") String payNo){
        this.payService.confirmPayHandle(payNo);
        return  REDIRECT_TO_ORDER_LIST_URL;
    }
}
