package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Data
@TableName(value = "read_info", autoResultMap = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadInfo {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("内容")
    private String value;
    @ApiModelProperty
    private Integer user_id;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Attrs> attrs;
    private boolean is_deleted;
    public ReadInfo(Integer id, String value) {
        this.id = id;
        this.value=value;
    }

    @PostConstruct
    public void getInfo() {
        System.out.println("@PostConstruct");
    }

    public void init() {
        System.out.println("@init...");
    }

    // 在容器销毁（移除）对象之前调用
    public void destory() {
        System.out.println("dog...@PreDestroy...");
    }


}
