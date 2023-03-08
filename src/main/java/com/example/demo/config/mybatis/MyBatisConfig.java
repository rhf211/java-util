package com.example.demo.config.mybatis;

import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@MapperScan("com.example.demo.dao")
@Component
public class MyBatisConfig implements CommandLineRunner {

    //引入分页插件

    /**
     * 可以set进去自己的
     */
    @Override
    public void run(String... args) throws Exception {
        JacksonTypeHandler.setObjectMapper(new ObjectMapper());
        GsonTypeHandler.setGson(new Gson());
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(1000L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}