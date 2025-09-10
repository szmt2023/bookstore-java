package com.atguigu.services;

import com.atguigu.pojo.User;

/**
 * @Author: ljg
 * @Date: 2025/9/5 11:05 AM Friday
 * @Description:
 */
public interface UserService {
    /**
     * 用户登录
     * @param user 要登录的用户
     * @return 查询后的数据库用户
     */
    User login(User user);

    /**
     * 用户注册
     * @param user 注册的用户信息
     * @return boolean
     */
    boolean register(User user);

    boolean isUsernameExists(String username);
}
