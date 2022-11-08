package com.example.demo.aop;

import com.example.demo.service.LookService;
import com.example.demo.service.impl.LookServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
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

    //切点可以配置自定义注解使用，也可扫描固定的位置进行处理
    @Pointcut("@annotation(com.example.demo.aop.BusinessLog)")
    //@Pointcut("execution( * com.example.demo.controller.*.*(..))")
    public void logPoint(){

    }
    @Pointcut("@annotation(com.example.demo.aop.NeedLogin)")
    public void loginPoint(){

    }

    //@AfterReturning(value = "@annotation(com.example.demo.aop.BusinessLog)")
    @AfterReturning("logPoint()")
    public void busLogAdvice(JoinPoint joinPoint) {
        System.out.println("后置切面启动");
    }

    @Before("logPoint()")
    public void busLogAdviceBefore(JoinPoint joinPoint) {
        //1.获取到所有的参数值的数组
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //2.获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        Method method = methodSignature.getMethod();
        System.out.println("---------------参数列表开始-------------------------");
        for (int i =0 ,len=parameterNames.length;i < len ;i++){
            System.out.println("参数名："+ parameterNames[i] + " = " +args[i]);
        }

        System.out.println("前置切面启动");
    }

    @Before("loginPoint()")
    public void loginAdviceBefore(JoinPoint joinPoint) {

    }
}
