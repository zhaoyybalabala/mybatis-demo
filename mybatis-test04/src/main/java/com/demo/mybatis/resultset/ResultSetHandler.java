package com.demo.mybatis.resultset;

import com.demo.mybatis.binding.MapperMethod;

import java.sql.PreparedStatement;

/**
 * com.demo.mybatis.resultset
 *
 * @author Zyy
 * @date 2019/2/3 17:52
 */
public interface ResultSetHandler {

    <T> T handler(PreparedStatement preparedStatement, MapperMethod mapperMethod);
}
