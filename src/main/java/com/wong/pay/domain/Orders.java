package com.wong.pay.domain;

import com.wong.pay.util.alipay.AlipayConfig;
import com.wong.pay.util.alipay.AlipayUtil;

public class Orders {
    private String outTradeNo;
    private String totalAmount;
    private String sellerId;
    private String body;
    private String createTime;
    private int deleted;

    public Orders(String body, String totalAmount) {
        this.body = body;
        this.totalAmount = totalAmount;
        this.outTradeNo = AlipayUtil.getOutTradeNo();
        this.sellerId = AlipayConfig.getSELLERID();
        this.createTime = AlipayUtil.getNowTime();
        this.deleted = 0;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "outTradeNo='" + outTradeNo + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", body='" + body + '\'' +
                ", deleted=" + deleted +
                '}';
    }


    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
