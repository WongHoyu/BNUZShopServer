package com.wong.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.wong.pay.dao.RefundOrderMapper;
import com.wong.pay.service.RefundOrderService;
import com.wong.pay.util.alipay.AlipayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefundOrderServiceImpl implements RefundOrderService {

    @Autowired
    RefundOrderMapper refundOrderMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String refundOrder(String outTradeNo, String totalMount) {
        AlipayUtil alipayUtil = AlipayUtil.ALIPAY_UTIL;
        // 单例获取 AlipayClient
        AlipayClient client = alipayUtil.getClientInstance();

        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        // 退款提示信息
        String tips = "";

        model.setOutTradeNo(outTradeNo);
        model.setRefundAmount(totalMount);
        request.setBizModel(model);


        try {
            // 获得支付宝响应参数
            AlipayTradeRefundResponse response = client.sdkExecute(request);

            // 获取状态码
            String responseCode = response.getCode(), refundOrderQueryCode = refundOrderQuery(outTradeNo);

            if ("10000".equals(responseCode) && "10000".equals(refundOrderQueryCode)) {
                // 设置订单被退款
                refundOrderMapper.deleteOrder(outTradeNo, response.getGmtRefundPay().toString());
                tips = response.getMsg();
            } else {
                System.out.println("responseCode: " + responseCode + " \nrefundOrderQueryCode: " + refundOrderQueryCode);
                tips = response.getSubMsg();
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(tips);
        return tips;
    }

    @Override
    public String refundOrderQuery(String outTradeNo) {
        AlipayUtil alipayUtil = AlipayUtil.ALIPAY_UTIL;
        // 单例获取 AlipayClient
        AlipayClient client = alipayUtil.getClientInstance();

        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();

        model.setOutTradeNo(outTradeNo);
        // 因为退款就退全款，所以我没有在申请订单退款时设置退款申请号，所以这里可以传订单号
        model.setOutRequestNo(outTradeNo);
        request.setBizModel(model);

        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = client.sdkExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return response.getCode();
    }


}
