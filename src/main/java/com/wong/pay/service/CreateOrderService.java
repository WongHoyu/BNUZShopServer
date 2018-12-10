package com.wong.pay.service;

import com.wong.pay.domain.Orders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface CreateOrderService {
    // 将订单持久化
    String addOrder(Orders orders);

    // 异步通知检查
    String asyncNotifyCheck(HttpServletRequest request, HttpServletResponse response);

    // 同步通知
    void syncNotify(HttpServletRequest request, HttpServletResponse response);
}
