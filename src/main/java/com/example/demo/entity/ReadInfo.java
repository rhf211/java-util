package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@TableName("lqq")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadInfo {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("内容")
    private String value;
    @ApiModelProperty
    private String sms_id;
}
