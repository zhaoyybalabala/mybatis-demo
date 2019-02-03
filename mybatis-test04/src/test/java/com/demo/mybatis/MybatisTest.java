package com.demo.mybatis;

import com.demo.mybatis.mappers.UserMapper;
import com.demo.mybatis.pojo.User;
import com.demo.mybatis.session.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * PACKAGE_NAME
 *
 * @author Zyy
 * @date 2019/2/2 23:17
 */
public class MybatisTest {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = MybatisTest.class.getClassLoader().getResourceAsStream(resource);
        Configuration configuration = new Configuration();
        configuration.setInputStream(inputStream);
        DefaultSqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        DefaultSqlSession sqlSession = (DefaultSqlSession)sqlSessionFactory.openSession(configuration);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByPrimaryKey(1);
        System.out.println(user);
    }


}
