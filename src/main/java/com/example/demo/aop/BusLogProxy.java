package com.example.demo.aop;

import com.example.demo.service.LookService;
import com.example.demo.service.impl.LookServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BusLogProxy implements InvocationHandler {

    private Object object;
    public BusLogProxy (Object o){
        this.object=o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置方法增强");
        method.invoke(proxy,args);
        System.out.println("后置方法增强");
        return null;
    }
}
