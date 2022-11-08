package com.example.demo.entity;

import lombok.Data;

/**
 * @author: 阮晗飞
 * @date: 2022/10/8
 */
@Data
public class UserDto {
    Long tenantUserId;
    Long tenantId;
    String tenantCode;
    Long authUserId;
    String authUsername;
    String name;
    Long profileId;
    boolean console;
    String phoneNo;
    String email;
    String platform;
    String status;

}
