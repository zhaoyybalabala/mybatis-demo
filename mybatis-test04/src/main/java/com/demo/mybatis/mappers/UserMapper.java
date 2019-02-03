package com.demo.mybatis.mappers;

import com.demo.mybatis.pojo.User;

/**
 * com.demo.mybatis.mappers
 *
 * @author Zyy
 * @date 2019/2/2 23:23
 */
public interface UserMapper {

    User selectByPrimaryKey(int id);
}
