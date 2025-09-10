package com.atguigu.dao;

import com.atguigu.pojo.User;

import java.util.Optional;

/**
 * @Author: ljg
 * @Date: 2025/9/5 11:03 AM Friday
 * @Description:
 */
public interface UserDao {
    User findUserByUP(User user);
    int insertUser(User user);

    Optional<User> findUserByUsername(String username);
}
