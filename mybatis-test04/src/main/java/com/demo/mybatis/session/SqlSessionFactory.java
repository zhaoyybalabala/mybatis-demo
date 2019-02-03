package com.demo.mybatis.session;

/**
 * com.demo.mybatis.session
 *
 * @author Zyy
 * @date 2019/2/2 23:03
 */
public interface SqlSessionFactory {
    SqlSession openSession(Configuration configuration);
}
