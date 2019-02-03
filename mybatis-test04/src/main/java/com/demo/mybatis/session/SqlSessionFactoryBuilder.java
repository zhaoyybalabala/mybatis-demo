package com.demo.mybatis.session;

import java.io.IOException;

/**
 * com.demo.mybatis.session
 *
 * @author Zyy
 * @date 2019/2/2 23:14
 */
public class SqlSessionFactoryBuilder {

    public DefaultSqlSessionFactory build(Configuration configuration) throws IOException {
        configuration.loadConfigurations();
        return new DefaultSqlSessionFactory();
    }

}
