package com.example.demo.aop;

import com.example.demo.service.LookService;
import com.example.demo.service.impl.LookServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class BusLogAspect {

    @Pointcut("@annotation(com.example.demo.aop.BusinessLog)")
    //@Pointcut("execution( * com.example.demo.controller.*.*(..))")
    public void logPoint(){

    }

    //@AfterReturning(value = "@annotation(com.example.demo.aop.BusinessLog)")
    @AfterReturning("logPoint()")
    public void busLogAdvice(JoinPoint joinPoint) {
        System.out.println("后置切面启动");
    }

    @Before("logPoint()")
    public void busLogAdviceBefore(JoinPoint joinPoint) {
        System.out.println("前置切面启动");
    }
}
