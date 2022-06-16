package com.example.demo.service.impl;

import com.example.demo.service.Pay;

import java.util.ArrayList;
import java.util.List;

public class ResChainHandler {
    private List<Pay> payChain = new ArrayList<>();

    public ResChainHandler() {

    }

    public void add(Pay pay) {
        payChain.add(pay);
    }

    public void handler() {
        payChain.forEach(pay -> pay.payInfo());
    }

}
