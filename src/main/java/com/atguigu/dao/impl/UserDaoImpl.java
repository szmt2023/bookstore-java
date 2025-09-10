package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

import java.util.Optional;

/**
 * @Author: ljg
 * @Date: 2025/9/5 11:03 AM Friday
 * @Description:
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public User findUserByUP(User user) {
        String sql = "SELECT * FROM tb_user WHERE username = ? AND password = ?";
        System.out.println("user = " + user);
        User dbUser = this.getBean(User.class, sql, user.getUsername(), user.getPassword());
        return dbUser;
    }

    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO tb_user(id, username, password, email) VALUES(NULL, ?, ?, ?)";
        int effectRow = this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return effectRow;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        String sql = "SELECT * FROM tb_user WHERE username = ?";
        User userDb = this.getBean(User.class, sql, username);
        return Optional.ofNullable(userDb);
    }
}
