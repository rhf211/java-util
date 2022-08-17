package com.example.demo.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class BusWebConfigurer implements WebMvcConfigurer {

    @Autowired
    BusLogInvocationHandler busLogInvocationHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(busLogInvocationHandler)
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**", "/webjars/**");
    }
}
