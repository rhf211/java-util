package com.example.demo.config;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author: 阮晗飞
 * @date: 2022/11/16
 */
//@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println(body);
        getFieldValueByFieldName("Date",body);
        System.out.println(returnType);
        System.out.println(selectedContentType);
        return null;
    }

    public static String getFieldValueByFieldName(String fieldName, Object object) {
        try {

            Field[] declaredFields = object.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Class<?> type = declaredField.getType();
            }
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            Object hisValue = field.get(object);
            if (null == hisValue) {
                return "";
            }

            String value = "";
            String type = field.getType().toString();
            if (type.contains("Date")) {
                value = DateFormatUtils.format((Date) hisValue, "yyyy-MM-dd HH:mm:ss");
            } else {
                value = hisValue.toString();
            }

            return value;
        } catch (Exception e) {

            return "";
        }
    }

}
