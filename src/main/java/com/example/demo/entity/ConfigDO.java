package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("config")
public class ConfigDO {
    private Integer id;
    private String value;
}
