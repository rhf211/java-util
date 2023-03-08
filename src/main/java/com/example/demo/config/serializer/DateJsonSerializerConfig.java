package com.example.demo.config.serializer;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.demo.config.serializer.DateConverterConfig;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.*;

/**
 * @author: 阮晗飞
 * @date: 2022/11/17
 * POST请求中 将请求体中的日期进行序列化
 * 支持场景：
 * *   <br/> 1. POST-application/json请求(RequestBody参数)在使用javabean作为入参时,javabean对象中的Date、LocalDateTime类型可以根据请求头中的时区字段自动转为应用服务当前时区的时间
 * *   <br/> 2. 接口返回对象时,对象中的Date、LocalDateTime类型的日期值,可以根据请求头中的时区字段,自动转为该时区的时间 * </p>
 * * </p>
 */
@JsonComponent
public class DateJsonSerializerConfig {

    /**
     * 反序列化，其它时区的时间转为本地时间
     */
    public static class LocalDateTimeJsonDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            // TODO 根据时区字段将日期转为本地时区时间
            //解析Json
            TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);

            //遍历Json字符串里面存在属性，并存在set中
            if (treeNode instanceof TextNode) {
                TextNode node = (TextNode) treeNode;
                String s = node.textValue();
                Date date = DateConverterConfig.dateCoverterUtil(TimeZone.getTimeZone("Asia/Tokyo"), TimeZone.getDefault(), TimeZone.getDefault(), s);
                return date;
            }
          /*  Iterator<String> iterator = treeNode.fieldNames();
          Set<String> fieldSet = new HashSet<>();
            while (iterator.hasNext()) {
                fieldSet.add(iterator.next());
            }
            //创建实例
            for (String s : fieldSet) {
                System.out.println(s);
            }*/
            //获取Class的所有属性
            return null;
        }
    }

    /**
     * 序列化，本地时间转为其它时区的时间
     */
    public static class LocalDateTimeJsonSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            // TODO 本地时间转对应时区的时间 date 转 string
            TimeZone aDefault = TimeZone.getDefault();
            try {
                if (ObjectUtil.isNotNull(localDateTime)) {
                    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
                    String s = DateUtil.formatDateTime(localDateTime);
                    jsonGenerator.writeString(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                TimeZone.setDefault(aDefault);
            }

        }
    }
}
