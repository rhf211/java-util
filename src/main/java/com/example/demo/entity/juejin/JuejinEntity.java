package com.example.demo.entity.juejin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class JuejinEntity {
    private Integer errNo;
    private String errMsg;
    private List<JueJinInfo> data;
    private String cursor;
    private Integer count;
    private Boolean hasMore;
}
