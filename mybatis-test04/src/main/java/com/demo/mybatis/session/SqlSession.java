package com.demo.mybatis.session;

import com.demo.mybatis.binding.MapperMethod;

import java.sql.SQLException;

/**
 * com.demo.mybatis.session
 *
 * @author Zyy
 * @date 2019/2/2 21:59
 */
public interface SqlSession {

    <T> T selectOne(MapperMethod id, Object param) throws SQLException;
}
