package com.example.demo.config.serializer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author: 阮晗飞
 * @date: 2022/11/17
 * 支持场景：
 * GET请求及POST表单请求(RequestParam和PathVariable参数)中日期字符串在转为Date、LocalDateTime类型时可以自动转为应用服务当前时区的时间
 * </p>
 */
@Component
public class DateConverterConfig implements Converter<String, Date> {


    @Override
    public Date convert(String s) {
        TimeZone defaultZone = TimeZone.getDefault();
        String timeZone = "Asia/Tokyo";
        TimeZone oriZone = TimeZone.getTimeZone(timeZone);
        Date date = dateCoverterUtil(oriZone, defaultZone, defaultZone, s);
        return date;
    }

    public static Date dateCoverterUtil(TimeZone oriZone, TimeZone tarZone, TimeZone defaltZone, String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("用户所在时区和时间" + oriZone.getID() + "," + s);
            sdf.setTimeZone(oriZone);
            Date dateTime = sdf.parse(s);
            System.out.println("用户所在时区时间戳" + dateTime.getTime());

            //将用户时区的字符串转换为系统时区的字符串
            sdf.setTimeZone(tarZone);
            String format = sdf.format(dateTime);
            System.out.println("转换为系统时区后的时间" + tarZone.getID() + "," + format);
            TimeZone.setDefault(tarZone);
            return sdf.parse(format);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TimeZone.setDefault(defaltZone);
        }
        return null;
    }
}
