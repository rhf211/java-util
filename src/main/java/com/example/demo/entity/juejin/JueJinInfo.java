package com.example.demo.entity.juejin;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JueJinInfo {

    private Integer msgId;

    private MsgInfo msgInfo;
}
