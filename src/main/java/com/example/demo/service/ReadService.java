package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.ReadInfo;
import com.example.demo.entity.ReadInfoDto;

import java.util.Date;

public interface ReadService {
    IPage<ReadInfo> getReader(Date date);
    IPage<ReadInfo> getReader(ReadInfoDto readInfo);

    void saveInfo();

    void seconds(Long id);

    void timeZoneTest();

}
