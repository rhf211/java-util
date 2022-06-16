package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JdGoods {
    private String id;
    private String score;
    private String img;
    private String name;
    private String price;
}
