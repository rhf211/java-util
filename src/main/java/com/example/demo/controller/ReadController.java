package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.ReadInfo;
import com.example.demo.entity.ReadInfoDto;
import com.example.demo.service.ReadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "Read控制器")
@RestController
@RequestMapping("/read")
public class ReadController {
    @Autowired
    private ReadService buffer;

    @ApiOperation("Read Get")
    @PostMapping("/get")
    public IPage<ReadInfo> getInfo(@RequestBody ReadInfoDto info) {
        return buffer.getReader(info);
    }

    @ApiOperation("Read Get")
    @GetMapping("/get/test")
    public IPage<ReadInfo> getInfoByDate( Date date) {
        return buffer.getReader(date);
    }

    @ApiOperation("save Info")
    @GetMapping("/save")
    public void saveInfo(){
      buffer.saveInfo();
    }

    @GetMapping("/seconds")
    public void seconds(Long id){
       buffer.seconds(id);
    }
    @GetMapping("tests")
    public void timeZoneTest(){
        buffer.timeZoneTest();
    }

}
