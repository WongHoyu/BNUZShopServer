package com.wong.pay.test;

import com.wong.pay.PayApplicationTests;
import com.wong.pay.controller.OrderController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class deleteTest extends PayApplicationTests {
    @Autowired
    OrderController controller;

    @Test
    public void failureDelete1() {
        controller.refundOrder("20181210032414294780945458023409408", "1");
    }

    @Test
    public void successDelete() {
        controller.refundOrder("20181210032414294780945458023409408", "100");
    }

    @Test
    public void failureDelete2() {
        controller.refundOrder("123","100");
    }
}
