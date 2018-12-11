package com.wong.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.wong.pay.dao.CreateOrderMapper;
import com.wong.pay.domain.Orders;
import com.wong.pay.service.CreateOrderService;
import com.wong.pay.util.alipay.AlipayConfig;
import com.wong.pay.util.alipay.AlipayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    @Autowired
    CreateOrderMapper createOrderMapper;


    /**
     * 生成订单
     *
     * @param orders
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String addOrder(Orders orders) {
        // 持久化订单
        createOrderMapper.insertOrder(orders.getOutTradeNo(), orders.getTotalAmount(), orders.getSellerId(), orders.getBody(), orders.getCreateTime());
        return getOrderString(orders);
    }


    private String getOrderString(Orders orders) {

        // 支付宝自动加签后，返回给前端处理
        String orderString = "";

        // 单例模式
        AlipayUtil alipayUtil = AlipayUtil.ALIPAY_UTIL;

        // 获取AlipayClient
        AlipayClient alipayClient = alipayUtil.getClientInstance();

        AlipayTradeAppPayRequest appPayRequest = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel appPayModel = new AlipayTradeAppPayModel();

        /*
         * 支付宝SDK已封装好公共请求参数，只需要请求业务参数。
         * 调用AlipayTradeAppPayModel，传入业务参数
         */
        // 一笔交易的具体描述信息
        appPayModel.setBody(orders.getBody());
        // 订单编号
        appPayModel.setOutTradeNo(orders.getOutTradeNo());
        appPayModel.setTimeoutExpress("30m");
        appPayModel.setTotalAmount(orders.getTotalAmount());
        appPayModel.setProductCode(AlipayConfig.getProductCode());
        appPayModel.setTimeExpire(orders.getCreateTime());
        // TODO notify_url必须为公网网址
        appPayRequest.setNotifyUrl("");
        appPayRequest.setBizModel(appPayModel);


        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse appPayResponse = alipayClient.sdkExecute(appPayRequest);
            orderString = appPayResponse.getBody();

        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            System.out.println(orderString);
        }

        return orderString;
    }


}
