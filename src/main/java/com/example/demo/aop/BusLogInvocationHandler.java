package com.example.demo.aop;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Enumeration;

/**
 * 对所有请求进行拦截做相关处理
 */
@Component
public class BusLogInvocationHandler implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse resp, Object handler) throws ParseException {
        if (handler instanceof ResourceHttpRequestHandler) {
            //静态资源请求
            return true;
        } else if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            NeedLogin needLogin = handlerMethod.getMethod().getAnnotation(NeedLogin.class);

            if (needLogin != null) {
                HttpSession session = request.getSession(false);
                String id = session.getId();
                System.out.println("需要登录" + id);
                return false;

            } else {

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        System.out.println("请求完成");
    }
}
