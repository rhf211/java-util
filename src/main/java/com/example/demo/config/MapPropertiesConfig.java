package com.example.demo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取配置文件加载到map中
 */
@Component
public class MapPropertiesConfig {
    private static Map<String, String> map = new HashMap();

    public static Map<String, String> getMap() {
        return map;
    }

      static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        //处理映射配置信息
        Properties mappingProperties = new Properties();
        InputStream mappingPropertiesInStream = loader.getResourceAsStream("blacklist.properties");
        try {
            mappingProperties.load(mappingPropertiesInStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                mappingPropertiesInStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Enumeration mappingPropertiesEenumeration = mappingProperties.propertyNames();
        while (mappingPropertiesEenumeration.hasMoreElements()) {
            String key = (String) mappingPropertiesEenumeration.nextElement();
            String value = mappingProperties.getProperty(key);
            map.put(key, value);
            System.out.println(key +"---" +value);
        }
    }
}
