package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.config.serializer.DateJsonSerializerConfig;
import com.example.demo.typehandler.AttrTypeHandler;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName(value = "read_info", autoResultMap = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadInfo implements Serializable {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("内容")
    private String value;
    @ApiModelProperty
    private Integer user_id;
    @TableField(typeHandler = AttrTypeHandler.class)
    private List<Attrs> attrs;
    @TableLogic
    private boolean is_deleted;
    private Date created;
    private Date create_union;

    public ReadInfo(Integer id, String value) {
        this.id = id;
        this.value = value;
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
