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
 * @Date: 2025/8/28 10:42 AM Thursday
 * @Description:
 */
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boolean flag = userService.register(user);
        if(flag) {
            // 注册成功重定向到登录页
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.html");
        } else {
            // 注册失败转发到注册页面
            req.getRequestDispatcher("/pages/user/register.html").forward(req, resp);
        }


    }
}
