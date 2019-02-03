package com.demo.mybatis.resultset;

import com.demo.mybatis.binding.MapperMethod;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Zyy
 * @date 2019/2/3 18:01
 *
 *  将结果反射賦值
 */
public class DefaultResultSetHandler implements ResultSetHandler {
    public <T> T handler(PreparedStatement preparedStatement, MapperMethod mapperMethod) {
        Object resultObject = null;
        try {
            resultObject = new DefaultObjectFactory().create(mapperMethod.getType());
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                int i = 0;
                //循环赋值
                for (Field field : resultObject.getClass().getDeclaredFields()) {
                    setValue(resultObject, field, resultSet, i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) resultObject;
    }

    private void setValue(Object resultObject, Field field, ResultSet resultSet, int i) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method method = resultObject.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        method.invoke(resultObject, getResult(field, resultSet));
    }

    //首字母大写，例如：Name
    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }

    //获取结果
    private Object getResult(Field field, ResultSet resultSet) throws SQLException {
        Class<?> type = field.getType();
        if (Integer.class.equals(type)) {
            return resultSet.getInt(field.getName());
        }
        if (String.class.equals(type)) {
            return resultSet.getString(field.getName());
        }
        return resultSet.getString(field.getName());
    }


}
