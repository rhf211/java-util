package com.example.demo.util.excelUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.List;
import java.util.Map;

/**
 * @author: 阮晗飞
 * @date: 2022/11/11
 */
public class ExcelUtils {
    public static void main(String[] args) {
        //createExcel();
        readExcel();


    }

    public static void createExcel() {
        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\10286\\Desktop\\work\\test.xlsx");
        //通过构造方法创建writer
        //ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");

        //跳过当前行，既第一行，非必须，在此演示用
        //writer.passCurrentRow();

        //合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "测试标题");
        //一次性写出内容，强制输出标题
        writer.write(rows, true);
        //关闭writer，释放内存
        writer.close();
    }

    public static void  readExcel(){
        //获取指定sheet
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:\\Users\\10286\\Desktop\\work\\app.xlsx"), 0);

        //获取指定行
        List<Object> objects = reader.readRow(0);

        //获取所有行list
        List<List<Object>> read = reader.read();

        //获取所有行map
        List<Map<String, Object>> maps = reader.readAll();

        List<ExeclDto> execlDtos = reader.readAll(ExeclDto.class);
    }
}
