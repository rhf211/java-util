package com.example.demo.util;

import com.example.demo.config.SpringContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Objects;

/**
 * @author: 阮晗飞
 * @date: 2022/9/7
 */
public class ConfigCenterWrapper {
    private static final Environment ENVIRONMENT;

    static {
        ENVIRONMENT = SpringContext.getApplicationContext().getEnvironment();
        Objects.requireNonNull(ENVIRONMENT);
    }
    public static String getCommonConfig(String key){
        return getCommonConfig(key,"");
    }
    public static String getCommonConfig(String key,String defaultValue){
        return ENVIRONMENT.getProperty(key,defaultValue);
    }
}
