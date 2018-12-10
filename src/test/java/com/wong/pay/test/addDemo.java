package com.wong.pay.test;

import com.wong.pay.PayApplicationTests;
import com.wong.pay.controller.OrderController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class addDemo extends PayApplicationTests {

    @Autowired
    OrderController controller;

    @Test
    public void addTest() {
        String orderMessage = controller.addOrder("测试商品", "100");
        System.out.println(orderMessage);
    }
}
