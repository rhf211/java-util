package com.example.demo.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@DS("slave")
@Mapper
public interface ConfigDao extends BaseMapper<ConfigDO> {
}

