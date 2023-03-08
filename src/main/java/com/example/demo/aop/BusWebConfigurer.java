package com.example.demo.aop;

import com.example.demo.config.serializer.DateConverterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class BusWebConfigurer implements WebMvcConfigurer {

    @Autowired
    BusLogInvocationHandler busLogInvocationHandler;

    @Autowired
    DateConverterConfig converter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(busLogInvocationHandler)
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**", "/webjars/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(converter);
    }
}
