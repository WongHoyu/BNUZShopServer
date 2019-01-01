package com.wong.pay.test;

import com.wong.pay.PayApplicationTests;
import com.wong.pay.controller.OrderController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class addDemo extends PayApplicationTests {

    @Resource
    OrderController controller;

    @Test
    public void addTest() {
        String orderMessage = controller.addOrder("测试商品3", "0.1");
        System.out.println(orderMessage);
    }
}
