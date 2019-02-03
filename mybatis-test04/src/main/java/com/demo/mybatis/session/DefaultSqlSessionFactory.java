package com.demo.mybatis.session;

import com.demo.mybatis.executor.SimpleExecutor;

/**
 * com.demo.mybatis.session
 *
 * @author Zyy
 * @date 2019/2/2 23:04
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    public SqlSession openSession(Configuration configuration) {
        return new DefaultSqlSession(configuration,new SimpleExecutor(configuration));
    }
}
