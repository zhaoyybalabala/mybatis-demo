package com.demo.mybatis.session;

import com.demo.mybatis.binding.MapperMethod;
import com.demo.mybatis.binding.MapperProxy;
import com.demo.mybatis.executor.Executor;

import java.lang.reflect.Proxy;
import java.sql.SQLException;


/**
 * com.demo.mybatis.session
 *
 * @author Zyy
 * @date 2019/2/2 22:00
 */
public class DefaultSqlSession implements SqlSession{
    private Configuration configuration;
    private Executor executor;

    public <T> T selectOne(MapperMethod mapperMethod, Object statement) throws SQLException {
        return executor.query(mapperMethod,statement);
    }

    public <T> T getMapper(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type},new MapperProxy<T>(this,type));
    }

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public Configuration getConfiguration() {
        return configuration;
    }


}
