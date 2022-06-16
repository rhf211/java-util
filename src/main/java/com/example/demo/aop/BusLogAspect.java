package com.example.demo.aop;

import com.example.demo.service.LookService;
import com.example.demo.service.impl.LookServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class BusLogAspect {


    @AfterReturning(value = "@annotation(com.example.demo.aop.BusinessLog)")
    public void busLogAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) (joinPoint.getSignature());
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        String[] params = signature.getParameterNames();
        try {
            Class<LookServiceImpl> aClass = LookServiceImpl.class;
            Method[] methods = aClass.getMethods();
            for (Method method1 : methods) {
                if (method1.getName().equals("test")) {
                    method1.invoke(aClass.newInstance(), "s");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("后置切面启动");
    }

    @Before(value = "@annotation(com.example.demo.aop.BusinessLog)")
    public void busLogAdviceBefore(JoinPoint joinPoint) {
        System.out.println("前置切面启动");
    }
}
