package com.example.demo.jobhndler;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestJobHandler {

    @XxlJob("GetInfo")
    public void GetInfo(){
        System.out.println("getInfo"+new Date());
    }
}
