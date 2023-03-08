package com.example.demo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.demo.aop.BusinessLog;
import com.example.demo.config.MapPropertiesConfig;
import com.example.demo.mq.provider.TestProvider;
import com.example.demo.service.LookService;
import org.checkerframework.checker.units.qual.A;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

@Service
public class LookServiceImpl implements LookService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestProvider testProvider;

    @Autowired
    private RedissonClient redissonClient;



    @Override
    @BusinessLog
    public String testLook() {
        Map<String, String> map = MapPropertiesConfig.getMap();
        System.out.println("执行业务代码");
        String name = Thread.currentThread().getName();
        return name;
    }

    @Override
    @BusinessLog
    public String testLook1() {
        Map<String, String> map = MapPropertiesConfig.getMap();
        System.out.println("执行业务代码2");
        return "test2";
    }

    public void test(String s) {
        System.out.println("反射用的方法=" + s);
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.formatDateTime(new Date()).substring(11, 19));
    }


}
