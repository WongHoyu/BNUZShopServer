package com.wong.pay.service;

import com.wong.pay.domain.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface CreateOrderService {
    // 将订单持久化
    void persistenceOrder(Orders orders);

    // 打包订单
    String getOrderString(Orders orders);
}
