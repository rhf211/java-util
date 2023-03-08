package com.example.demo.config.serializer;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.Date;
import java.util.List;

/**
 * @author: 阮晗飞
 * @date: 2022/11/18
 */
public class MyBeanSerializerModifier extends BeanSerializerModifier {
    public final JsonSerializer serializer = new DateJsonSerializerConfig.LocalDateTimeJsonSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (int i = 0; i < beanProperties.size(); i++) {
            BeanPropertyWriter writer = beanProperties.get(i);
            Class<?> clazz = writer.getPropertyType();

            if (clazz.equals(Date.class)) {
                writer.assignSerializer(serializer);
            }
        }
        return beanProperties;
    }
}
