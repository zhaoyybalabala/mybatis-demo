package com.demo.mybatis.binding;

import com.demo.mybatis.session.DefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * com.demo.mybatis.binding
 *
 * @author Zyy
 * @date 2019/2/2 23:29
 */
public class MapperProxy<T> implements InvocationHandler {
    private final DefaultSqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(DefaultSqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //key : com.demo.mybatis.mapper.UserMapper.selectByPrimaryKey
        MapperMethod mapperMethod = sqlSession.getConfiguration().getMapperRegistry().getKnownMappers().get(
                method.getDeclaringClass().getName() + "." + method.getName());
        if (null != mapperMethod) {
            return sqlSession.selectOne(mapperMethod, args[0]);
        }
        return method.invoke(proxy, args);
    }
}
