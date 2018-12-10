package com.wong.pay.service;

import org.springframework.stereotype.Service;

@Service
public interface RefundOrderService {
    String refundOrder(String outTradeNo, String totalAmount);

    String refundOrderQuery(String outTradeNo);
}
