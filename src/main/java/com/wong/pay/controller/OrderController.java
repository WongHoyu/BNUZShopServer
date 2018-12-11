package com.wong.pay.controller;

import com.wong.pay.domain.Orders;
import com.wong.pay.service.impl.CreateOrderServiceImpl;
import com.wong.pay.service.impl.NotificationServiceImpl;
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

    private CreateOrderServiceImpl createOrderService;

    private RefundOrderServiceImpl refundOrderService;

    private NotificationServiceImpl notificationService;


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

        // 获取 CreateOrderServiceImpl 实例
        CreateOrderServiceImpl createOrderService = getCreateOrderService();

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
    public String asyncNotifyOrder(HttpServletRequest request, HttpServletResponse response) {
        // 获取 NotificationServiceImpl 实例
        NotificationServiceImpl notificationService = getNotificationService();

        return notificationService.asyncNotifyCheck(request, response);
    }


    /**
     * 同步通知
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "syncNotify")
    public void syncNotifyOrder(HttpServletRequest request, HttpServletResponse response) {
        // 获取 NotificationServiceImpl 实例
        NotificationServiceImpl notificationService = getNotificationService();

        notificationService.syncNotify(request, response);
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
        // 获取 RefundOrderServiceImpl 实例
        RefundOrderServiceImpl refundOrderService = getRefundOrderService();

        return refundOrderService.refundOrder(outTradeNo, totalMount);

    }


    private CreateOrderServiceImpl getCreateOrderService() {
        return createOrderService;
    }

    @Autowired
    private void setCreateOrderService(CreateOrderServiceImpl createOrderService) {
        this.createOrderService = createOrderService;
    }

    private RefundOrderServiceImpl getRefundOrderService() {
        return refundOrderService;
    }

    @Autowired
    private void setRefundOrderService(RefundOrderServiceImpl refundOrderService) {
        this.refundOrderService = refundOrderService;
    }

    private NotificationServiceImpl getNotificationService() {
        return notificationService;
    }

    @Autowired
    private void setNotificationService(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }
}
