package com.wong.pay.controller;

import com.wong.pay.domain.Orders;
import com.wong.pay.service.impl.CreateOrderServiceImpl;
import com.wong.pay.service.impl.RefundOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//证明是controller层并且返回json
@RestController
@RequestMapping()
public class OrderController {

    @Autowired
    private CreateOrderServiceImpl createOrderService;

    @Autowired
    private RefundOrderServiceImpl refundOrderService;


    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param body
     * @param totalAmount
     * @return
     */
    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    public String addOrder(String body, String totalAmount) {
        Orders orders = new Orders(body, totalAmount);

        // 处理订单
        return createOrderService.addOrder(orders);
    }



    /**
     * 异步通知
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "asyncNotify", method = RequestMethod.POST)
    public String asyncNotifyOrder(HttpServletRequest request,HttpServletResponse response) {
        return createOrderService.asyncNotifyCheck(request, response);
    }


    /**
     * 同步通知
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "syncNotify")
    public void syncNotifyOrder(HttpServletRequest request,HttpServletResponse response) {
        createOrderService.syncNotify(request, response);
    }


    /**
     * 退款
     *
     * @param outTradeNo
     * @param totalMount
     * @return
     */
    @RequestMapping(value = "refund")
    public String refundOrder(String outTradeNo, String totalMount) {
        return refundOrderService.refundOrder(outTradeNo, totalMount);

    }



}
