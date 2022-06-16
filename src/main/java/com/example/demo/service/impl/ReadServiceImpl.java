package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.ReaderDao;
import com.example.demo.entity.ReadInfo;
import com.example.demo.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadServiceImpl implements ReadService {
    @Autowired
    private ReaderDao readerDao;

    @Override
    public IPage<ReadInfo> getReader() {
        IPage<ReadInfo> readInfoIPage = readerDao.selectPage(new Page<ReadInfo>(0, 10), new QueryWrapper<>());
        List<ReadInfo> readInfos = readInfoIPage.getRecords();
        return readInfoIPage;

    }

    @Override
    public void saveInfo() {
        try {
            File file = new File("C:\\test\\txt80.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s;
            List<String> list = new ArrayList<>();
            while ((s = bufferedReader.readLine()) != null) {
                list.add(s);
            }
            bufferedReader.close();
            readerDao.batchInsert(list);
            System.out.println("持久化文件完成");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
