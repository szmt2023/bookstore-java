package com.atguigu.services.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.services.UserService;
import com.atguigu.utils.MD5Util;

import java.sql.SQLException;
import java.util.Optional;

/**
 * @Author: ljg
 * @Date: 2025/9/5 11:05 AM Friday
 * @Description:
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        // 1. 需要将用户密码进行加密处理
        String md5Password = MD5Util.encode(user.getPassword());
        user.setPassword(md5Password);

        return userDao.findUserByUP(user);
    }

    @Override
    public boolean register(User user) {
        String md5Password = MD5Util.encode(user.getPassword());
        user.setPassword(md5Password);

        int i = 0;
        try {
            i = userDao.insertUser(user);
        } catch(RuntimeException e) {
            Throwable cause = e.getCause();
            if(cause instanceof SQLException) {
                int errorCode = ((SQLException) cause).getErrorCode();
                // MYSQL: 1062(ER_DUP_ENTRY) 键冲突
                if(errorCode == 1062) {
                    return false;
                }
            }
        }
        return i > 0;
    }

    @Override
    public boolean isUsernameExists(String username) {
        Optional<User> optional = userDao.findUserByUsername(username);
        return optional.isPresent();
    }
}
