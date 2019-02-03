package com.demo.mybatis.executor;

import com.demo.mybatis.binding.MapperMethod;

import java.sql.SQLException;

/**
 * com.demo.mybatis.executor
 *
 * @author Zyy
 * @date 2019/2/2 21:57
 */
public interface Executor {

    <T> T  query(MapperMethod mapperMethod, Object params) throws SQLException;
}
