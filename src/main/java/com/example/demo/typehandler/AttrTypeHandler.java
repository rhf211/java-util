package com.example.demo.typehandler;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.example.demo.entity.Attrs;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

import static com.frameworkset.util.SimpleStringUtil.getObjectMapper;

/**
 * @author: 阮晗飞
 * @date: 2022/9/6
 */
public class AttrTypeHandler extends JacksonTypeHandler {
    public AttrTypeHandler(Class<Object> type) {
        super(type);
    }
    @Override
    protected Object parse(String json) {
        try {
            return getObjectMapper().readValue(json, new TypeReference<List<Attrs>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
