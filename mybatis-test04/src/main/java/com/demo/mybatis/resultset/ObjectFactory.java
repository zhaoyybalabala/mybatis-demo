package com.demo.mybatis.resultset;

import java.util.List;
import java.util.Properties;

/**
 * com.demo.mybatis.resultset
 *
 * @author Zyy
 * @date 2019/2/3 18:04
 * mybatis ：org.apache.ibatis.reflection.factory
 */
public interface ObjectFactory {
    void setProperties(Properties var1);

    <T> T create(Class<T> var1) throws Exception;

    <T> T create(Class<T> var1, List<Class<?>> var2, List<Object> var3) throws Exception;

    <T> boolean isCollection(Class<T> var1);
}
