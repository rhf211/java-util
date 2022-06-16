package com.example.demo.service.impl;

import com.example.demo.service.Pay;
import org.springframework.stereotype.Component;

@Component
public class AliPay extends Pay {
    @Override
    public String payInfo() {
        System.out.println("aliPay method");
        return "ali";
    }

    @Override
    public Boolean refund(String s) {
        if ("ali".equals(s)) {
            return true;
        }
        return false;
    }
}
