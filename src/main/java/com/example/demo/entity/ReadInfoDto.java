package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 阮晗飞
 * @date: 2022/11/18
 */
@Data
public class ReadInfoDto implements Serializable {

    ReadInfo readInfo;
    String orderTime;
}
