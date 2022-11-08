package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.JdGoods;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.Arrays;
import java.util.List;

/**
 * json和实体转换工具
 */
public class JsonUtil {
    public static void main(String[] args) {
        Gson gson = new Gson();
        //实体转json
        JdGoods jdGoods = new JdGoods("2", "2", "img1", "text", "price1");
        Object parse = JSONObject.toJSON(jdGoods);
        System.out.println(jdGoods.toString());
        System.out.println(parse.toString());
        //数组转json
        List<JdGoods> list = Arrays.asList(jdGoods);
        Object o = JSONObject.toJSON(list);
        System.out.println(o.toString());
        //json转实体
        String s = "{\"score\":\"2\",\"img\":\"img1\",\"price\":\"price1\",\"name\":\"text\",\"id\":\"2\"}";
        JdGoods good = gson.fromJson(s, new TypeToken<JdGoods>() {
        }.getType());
        System.out.println(good);
        String lis = "[{\"score\":\"2\",\"img\":\"img1\",\"price\":\"price1\",\"name\":\"text\",\"id\":\"2\"}]";
        List<JdGoods> jdGoods1 = gson.fromJson(lis, new TypeToken<List<JdGoods>>() {
        }.getType());

    }
}
