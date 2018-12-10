package com.wong.pay.util.alipay;

import com.alipay.api.AlipayConstants;

public class AlipayConfig {

    private static final String APPID = "";

    private static final String RSA_PRIVATE_KEY = "";

    private static final String ALIPAY_PUBLIC_KEY = "";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static final String notify_url = "http://www.xxx.com/alipay/notify_url.do";

    //5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    private static final String return_url = "http://www.xxx.com/alipay/return_url.do";

    // 6.请求支付宝的网关地址
    private static final String GATEWAY = "https://openapi.alipay.com/gateway.do";

    // 7.编码
    private static final String CHARSET = AlipayConstants.CHARSET_UTF8;

    // 8.返回格式
    private static final String FORMAT = AlipayConstants.FORMAT_JSON;

    // 9.加密类型
    private static final String SIGNTYPE = "RSA2";

    // 10. seller ID
    private static final String SELLERID = "13928542808";

    // 11. product code
    private static final String productCode = "QUICK_MSECURITY_PAY";


    // getter and setter


    public static String getProductCode() {
        return productCode;
    }

    public static String getSELLERID() {
        return SELLERID;
    }

    public static String getAPPID() {
        return APPID;
    }

    public static String getRsaPrivateKey() {
        return RSA_PRIVATE_KEY;
    }

    public static String getAlipayPublicKey() {
        return ALIPAY_PUBLIC_KEY;
    }

    public static String getNotify_url() {
        return notify_url;
    }

    public static String getReturn_url() {
        return return_url;
    }

    public static String getGateway() {
        return GATEWAY;
    }

    public static String getCHARSET() {
        return CHARSET;
    }

    public static String getFORMAT() {
        return FORMAT;
    }

    public static String getSIGNTYPE() {
        return SIGNTYPE;
    }
}