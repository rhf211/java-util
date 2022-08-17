package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.ReadInfo;

public interface ReadService {
    IPage<ReadInfo> getReader();

    void saveInfo();

    void seconds(Long id);
}
