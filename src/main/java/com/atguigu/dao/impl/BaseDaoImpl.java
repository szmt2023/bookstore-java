package com.atguigu.dao.impl;

import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/8/28 10:39 AM Thursday
 * @Description: 对 DBUtils 工具类的二次封装
 */
public abstract class BaseDaoImpl {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 通用的增删改方法
     * @param sql 要执行的 SQL
     * @param args 如果sql中有 ？，就要传入对应的值
     * @return int 执行的结果
     */
    protected int update(String sql, Object... args) {
        try {
            return queryRunner.update(JDBCUtils.getConnection(), sql, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param clazz 返回对象的 Class 对象
     * @param sql 要执行的sql语句
     * @param args sql语句带？就需要传入对应的参数
     * @return 一个对象
     * @param <T>
     */
    protected <T> T getBean(Class<T> clazz, String sql, Object... args) {
        List<T> list = getList(clazz, sql, args);
        if(list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    protected <T> List<T> getList(Class<T> clazz, String sql, Object... args) {
        try {
            return queryRunner.query(JDBCUtils.getConnection(), sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Object getValue(String sql, Object... args) {
        try {
            return queryRunner.query(JDBCUtils.getConnection(), sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void batch(String sql, Object[][] args) {
        try {
            queryRunner.batch(JDBCUtils.getConnection(), sql, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
