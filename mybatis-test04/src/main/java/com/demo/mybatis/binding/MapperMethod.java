package com.demo.mybatis.binding;

/**
 * com.demo.mybatis.binding
 *
 * @author Zyy
 * @date 2019/2/2 22:27
 *
 * 将解析的sql加载到这个类中
 * sql:select * from user
 * type:user
 */
public class MapperMethod<T> {
    private String sql;
    private Class<T> type;


    public MapperMethod(String sql, Class<T> type) {
        this.sql = sql;
        this.type = type;
    }

    public MapperMethod() {
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}
