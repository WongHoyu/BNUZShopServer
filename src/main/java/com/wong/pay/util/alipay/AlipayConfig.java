package com.wong.pay.util.alipay;

import com.alipay.api.AlipayConstants;

public class AlipayConfig {

    // 1.商户appid
    private static final String APPID = "2016091500515016";

    //2.私钥 pkcs8格式的
    private static final String RSA_PRIVATE_KEY ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCg2Wu0eoKIDP0ZF/W/gPPLjf6Rya41wkIbfg5MrLQDLasgqbUvPtbu8qcyGnEHiCAdvQhhbugIhmypuXcvDGDguumJUaWk4o1GD6MztCVeb3239YAuPnTPAqTQmMhpiPqDVU88q1PZ0emqfruR70hokJ5PwY5h+I7Tw+OyVYsBgvUeizm/208f1neZ1qhlMosWSlvZaRFJ1bhXusztbGAjzUt+p9cSd61VnSmEAOXKuDJN/tkRuzOdxGOesmmQyT3iHEb8j3EhRwQUD9ktGT39Ukjl+4CPjc5PNjcQgrZ3Fh4toSFrhXfU7QeMb56aKG93cQF0q5zfM9Kh2+P+pUzrAgMBAAECggEAamAf+NXkqbFdWVEdNy9DDGW17BijW+5C5gex0UeOLWPwfsN9jedTIefcZyhRhBeklcZEWpuMXMb56i08AyN+lmFGvYf/slIZ+DGzTyheZlv5msWDphGsEpO/CC1irVjnxB+RLaBo7e8DR7xUtCi5jF3INRre3TAS1T4BwhkMu0wh9dz9JpC5yyorihznIk1C2/VtI5drc3rzJSb/T/R6yPjOo1nvz6EqDh2+Ns1KIKPZ9S2o3tNDZtmdDZ4I0s5JGbGdogs19YO/9z5Waf4UNbD6PHUFnTTp7b+UlDPxLWbNy1uUsCYdkwNh0q+ezYnORsMMOn7ScAVNl6hyVan4AQKBgQDPIgaOaSpeU8rDwJBc8xRMXyemufG1vIj0JyXN3ZtcYCPhrFKv2ZrrBkaXfczSi39BasY6ElRA4pMZ+8VsDwew3+XeIP6l+05ARfwhTPuuelKDhY9fCR70DwhfgypKEWDQ0Tx4A6l3OGbP3N72Ac3yZESgeKylp4Ans7YNauyKAQKBgQDGzA483arg363eFyYJrPHVPdNyjO8ovSPmxBEW4g3V6M3n9cq7sNRirQpNt9a5IzzM4BrUDxPFdoqDEGcEJAxyKGNTobZV1zpUiEKhOlosCR39OFtkOFiH8gnwtxPmVc7YwOz71qwvVkpMA/77wICZNc0i6+N7rAm6VCoyflae6wKBgQCIyd+qjKj+QjfVroWn3J+7D9wPrxyA+FE5a+D7Yy4GgzcvAa5Zk0tLiolz4hAxuw3/FZRzJUee0gJzVmG6SvK5TCNx3jhNRPkYWR94nm5Xe1CiqsFT8aIPax4MdYKoB3H4dTWJzGFN6mF7RFG6BNcNDEqF7yuU+lkX3waLNeGgAQKBgCMd/tHfsoTg3nziB/UjmOC7eDO/E6xVpRbpwb8SFbGY227Da5m2Rf5cZPqPpB22TNJAMF0PgMWcCCHY7um7WHt+CPXIFkaZq5MtMt/6R92JisOlhfdxQKpbJkhgfuJsfAqsIfDm3inKi3tcJVnhipYF/APUOa4qGNnSblXXHYQbAoGASZxTreCFPg6RIngKnmh4ahikU2ZmtVD9IFaExFGqu5qIshHdAvix/LAvSKV3pi7asgjS4s90HvXP4fPjJuzFOrRZLhRkzwKucje8puxIzqV9LL/Qi/uDgbq/TS56dU/rEr/x9HiEzp/eA3xO/A+L1llBemYHZ4fO4J09j2JrmSY=";

    // 3.支付宝公钥
    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnMAzX9rtyJfrwsb/5vMFek2FsuMMlVNjzF816AAsPS3k6XZ3RzSudTF7ipkSh/k9HvvJr6/LsV7dl1INbu6hsqN+EAoFKoW0euTtKpV+85hwZ3VckGeOZVlh0gA6+othlyFIQAVOHPbrTdxXvXxgliB4krAncB81NKdXXEOOBrWIuzJKe1l7OgJZwsOJMnBNZZLfJfwB31vWyYraAGcLAbQZiB7Re3wLQtpT6NJ87clRovXgxakY41kc8D2O2e8Ulr6GIRAaU+oMmBmCkCv44xPzYqao+rId3WvpwV0S9aXj4NYSTL+4AwF4EHiH4n9ANGhIlE2JE1DzL+8Qn2pLcQIDAQAB";

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