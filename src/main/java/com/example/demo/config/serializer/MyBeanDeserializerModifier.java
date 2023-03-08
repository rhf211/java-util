package com.example.demo.config.serializer;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

import java.util.List;

/**
 * @author: 阮晗飞
 * @date: 2022/11/18
 */
public class MyBeanDeserializerModifier extends BeanDeserializerModifier {
    @Override
    public List<BeanPropertyDefinition> updateProperties(DeserializationConfig config, BeanDescription beanDesc, List<BeanPropertyDefinition> propDefs) {
        for (int i = 0; i < propDefs.size(); i++) {
            BeanPropertyDefinition definition = propDefs.get(i);

        }
        return null;
    }
}
