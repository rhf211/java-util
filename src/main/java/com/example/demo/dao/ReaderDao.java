package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ReadInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReaderDao extends BaseMapper<ReadInfo> {

   @Insert({"<script>",
            "insert into lqq ",
            "(value) ",
            "VALUES",
            "<foreach collection='list' item='item' separator=','>",
            "(#{item})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(List<String> list);

   void insertOrupdate(@Param("list") List<ReadInfo> readInfo);

    void batchUpdate(@Param("list")List<ReadInfo> asList);
}
