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
    private CreateOrderMapper createOrderMapper;


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


    /**
     * 异步通知
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String asyncNotifyCheck(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = getRequestMap(request.getParameterMap());

        return asyncNotifyCheck(params);
    }


    /**
     * 同步通知
     *
     * @param request
     * @param response
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void syncNotify(HttpServletRequest request, HttpServletResponse response) {
        // 需要转化 value[] 中的参数
        Map<String, String> syncParams = getRequestMap(request.getParameterMap());

        // 签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;

        try {
            signVerified = AlipaySignature.rsaCheckV1(syncParams, AlipayConfig.getAlipayPublicKey(), AlipayConfig.getCHARSET(), AlipayConfig.getSIGNTYPE());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if (signVerified && syncParams.get("trade_status").equals("TRADE_SUCCESS")) {
            // 同步结果仅仅作为一个支付结束的通知（忽略执行校验）
            // 更新订单结束时间
            createOrderMapper.updateOrderFinishedTime(syncParams.get("out_trade_no"), syncParams.get("timestamp"));
        }

    }

    private String asyncNotifyCheck(Map<String, String> params) {
        // 签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;

        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.getAlipayPublicKey(), AlipayConfig.getCHARSET(), AlipayConfig.getSIGNTYPE());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if (signVerified && params.get("trade_status").equals("TRADE_SUCCESS")
                && params.get("app_id").equals(AlipayConfig.getAPPID())) {
            // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，
            // 校验成功后在response中返回success，校验失败返回failure
            Orders orders = createOrderMapper.checkOrder(params.get("out_trade_no"), params.get("total_amount"), params.get("seller_id"));
            if (null != orders) {
                return "success";
            }
        } else {
            return "failure";
        }

        return "failure";
    }

    private Map<String, String> getRequestMap(Map<String, String[]> map) {
        // 从 request 中获取支付宝传来的参数
        Map<String, String> params = new HashMap<>();

        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String tempKey = entry.getKey();
            String[] tempValue = entry.getValue();

            int length = tempValue.length;

            StringBuilder values = new StringBuilder();
            for (int i = 0; i < length; i++) {
                values.append((i == length - 1) ? tempValue[i] : tempValue[i] + ",");
            }

            params.put(tempKey, values.toString());
        }

        return params;
    }

}
