package com.demo.mybatis.statement;

import com.demo.mybatis.binding.MapperMethod;
import com.demo.mybatis.jdbc.DBUtil;
import com.demo.mybatis.resultset.DefaultResultSetHandler;
import com.demo.mybatis.resultset.ResultSetHandler;
import com.demo.mybatis.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * com.demo.mybatis.statement
 *
 * @author Zyy
 * @date 2019/2/2 23:56
 * handler 最终干活的
 */
public class StatementHandler {
    private Configuration configuration;
    private ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        this.resultSetHandler = new DefaultResultSetHandler();
    }

    public <T> T query(MapperMethod mapperMethod, Object params) throws SQLException {
        Connection connection = DBUtil.open();
        //String.format(mapperMethod.getSql(), (Integer) params)) ==> select *  from user where id = 1
        PreparedStatement preparedStatement = connection.prepareStatement(String.format(mapperMethod.getSql(), (Integer) params));
        preparedStatement.executeQuery();
        //处理结果集
        return resultSetHandler.handler(preparedStatement, mapperMethod);
    }
}
