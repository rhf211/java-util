package com.example.demo.util;

import cn.hutool.core.lang.UUID;
import com.example.demo.entity.ReadInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: 阮晗飞
 * @date: 2022/8/24
 */
public class TestUtil {
    public static void main(String[] args) {
        ReadInfo readInfo=new ReadInfo();
        readInfo.setId(19);
        Integer integer = Optional.ofNullable(readInfo).map((aa)->aa.getId()).orElse(0);
        System.out.println(integer);

    }
}
