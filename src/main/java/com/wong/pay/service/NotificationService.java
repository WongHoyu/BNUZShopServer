package com.wong.pay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NotificationService {

    // 异步通知检查
    String asyncNotifyCheck(HttpServletRequest request, HttpServletResponse response);

    // 同步通知
    void syncNotify(HttpServletRequest request, HttpServletResponse response);

}
