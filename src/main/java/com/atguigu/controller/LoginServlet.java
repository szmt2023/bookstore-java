package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.services.UserService;
import com.atguigu.services.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: ljg
 * @Date: 2025/8/27 5:05 PM Wednesday
 * @Description:
 */

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * url: localhost:3000/bookstore/loginServlet
     * 参数：username password
     * 业务：根据用户名和密码查询数据库
     * 结果:
     *    有值：true 重定向到系统首页
     *    没值：false 转发到登录页面
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        User dbUser = userService.login(user);
        System.out.println("dbUser = " + dbUser);

        if(dbUser == null) {
            // 转发到登录页面
            // “/” 服务器解析，从 webapp 开始
            req.getRequestDispatcher("/pages/user/login.html").forward(req, resp);
        } else {
            // “/” 由浏览器解析，解析的是主机，没有上下文目录
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }
    }
}
