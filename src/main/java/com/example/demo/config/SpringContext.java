package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * applicatioContext 类
 *
 * @author 18834
 */
@Configuration
public class SpringContext implements ApplicationContextAware, BeanPostProcessor, EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(SpringContext.class);
    private static ApplicationContext application;
    private static Environment environment;

    public static ApplicationContext getApplicationContext() {
        return application;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.application = applicationContext;
        log.info("setApplicationContext");
    }

    /**
     * 获取当前环境
     *
     * @return
     */
    public static String getActiveProfile() {
        return environment.getActiveProfiles()[0];
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringContext.environment = environment;
    }
}