package com.example.demo.config;

import com.example.demo.entity.ReadInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: 阮晗飞
 * @date: 2022/9/15
 */
@Configuration
@PropertySource(value={"classpath:/blacklist.properties"})
public class TestConfig {

    //Constructor > @PostConstruct > InitializingBean > init-method

    @Value("${nickName}")
    private String nickName;
    @Bean(name = "readInfo",initMethod = "init",destroyMethod = "destory")
    public ReadInfo getRe(){
        ReadInfo readInfo= new ReadInfo();
        readInfo.setValue(nickName);
        return readInfo;
    }
}
