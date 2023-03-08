package com.example.demo.config.mybatis;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.formula.functions.T;

import java.sql.SQLException;
import java.util.TimeZone;

/**
 * @author: 阮晗飞
 * @date: 2022/11/16
 */
public class TimeZoneInnerInterceptor implements InnerInterceptor {


    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        System.out.println("执行拦截"+TimeZone.getDefault().getID());

        String buildSql = boundSql.getSql();
        try {
            Statement statement = CCJSqlParserUtil.parse(buildSql);
            Select select = (Select) statement;
            System.out.println("sdasd "+select.toString());
            PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
            mpBoundSql.sql(select.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    TimeZoneInnerInterceptor(){}
}
