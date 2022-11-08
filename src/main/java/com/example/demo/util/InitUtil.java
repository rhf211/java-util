package com.example.demo.util;

import org.checkerframework.checker.units.qual.A;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class InitUtil {

    @Autowired
    private ApplicationContext applicationContext;
    private static final List<String> urlList = new ArrayList<>();

   // @PostConstruct
    public void initMap() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        for (RequestMappingInfo requestMappingInfo : handlerMethods.keySet()) {
            Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
            Set<RequestMethod> methods = requestMappingInfo.getMethodsCondition().getMethods();
            System.out.println(methods.toString());
            for (String pattern : patterns) {
                urlList.add(pattern);
            }
        }
        System.out.println(urlList);

    }
}
