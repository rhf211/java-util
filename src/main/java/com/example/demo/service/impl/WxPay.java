package com.example.demo.service.impl;

import com.example.demo.service.Pay;
import org.springframework.stereotype.Component;

@Component
public class WxPay extends Pay {
    @Override
    public String payInfo() {
        System.out.println("wxPay method");
        return "wx";
    }

    @Override
    public Boolean refund(String s) {
        if ("wx".equals(s)) {
            return true;
        }
        return false;
    }
}
