package com.demo.mybatis.executor;

import com.demo.mybatis.binding.MapperMethod;
import com.demo.mybatis.session.Configuration;
import com.demo.mybatis.statement.StatementHandler;

import java.sql.SQLException;

/**
 * com.demo.mybatis
 *
 * @author Zyy
 * @date 2019/2/2 21:57
 * 执行器
 */
public class SimpleExecutor implements Executor{
    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T query(MapperMethod mapperMethod, Object params) throws SQLException {
        StatementHandler statementHandler = new StatementHandler(configuration);
        return statementHandler.query(mapperMethod,params);
    }
}
