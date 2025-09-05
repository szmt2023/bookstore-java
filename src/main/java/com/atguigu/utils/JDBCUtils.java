package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: ljg
 * @Date: 2025/8/27 5:13 PM Wednesday
 * @Description:
 */
public class JDBCUtils {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal;


    static {
        try {
            // 获取数据库连接信息
            Properties dbInfo = new Properties();
            dbInfo.load(JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));

            // 获取数据源
            dataSource = DruidDataSourceFactory.createDataSource(dbInfo);
            threadLocal = new ThreadLocal<>();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获取连接
    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if(connection == null) {
            try {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    // 释放连接
    public static void releaseConnection() {
        Connection connection = threadLocal.get();
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
