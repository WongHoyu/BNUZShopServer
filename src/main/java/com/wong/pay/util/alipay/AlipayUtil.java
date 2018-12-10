package com.wong.pay.util.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public enum AlipayUtil {
    // 唯一实例
    ALIPAY_UTIL;

    public static synchronized String getOutTradeNo() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(dateTimeFormatter) +  + getRandom();
    }

    public static String getNowTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(dateTimeFormatter);
    }

    public AlipayClient getClientInstance() {
        return new DefaultAlipayClient(AlipayConfig.getGateway(), AlipayConfig.getAPPID(),
                AlipayConfig.getRsaPrivateKey(), AlipayConfig.getFORMAT(), AlipayConfig.getCHARSET(),
                AlipayConfig.getAlipayPublicKey(), AlipayConfig.getSIGNTYPE());
    }


    /**
     * 生成固定长度随机码
     */
    private static long getRandom() {
        long min = 1, max = 9, length = 48;
        for (int i = 1; i < length; i++) {
            min *= 10;
            max *= 10;
        }
        return (((long) (new Random().nextDouble() * (max - min)))) + min;
    }


}
