package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.services.UserService;
import com.atguigu.services.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: ljg
 * @Date: 2025/9/5 2:13 PM Friday
 * @Description:
 */

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    // 跳到登录页
    public void toLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.processTemplate("user/login", req, resp);
    }

    // 跳到注册页
    public void toRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.processTemplate("user/register", req, resp);
    }

    // 登录

    /**
     * url: localhost:3000/bookstore/loginServlet
     * 参数：username password
     * 业务：根据用户名和密码查询数据库
     * 结果:
     *    有值：true 重定向到系统首页
     *    没值：false 转发到登录页面
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        // 1. 获取前端数据
        Map<String, String[]> parameterMap = req.getParameterMap();

        // 2. 封装成 user 对象
        User user = new User();
        BeanUtils.populate(user, parameterMap);

        // 3. 调用 userService.login(user) 方法处理
        User dbUser = userService.login(user);

        // 4. 如果登录成功，重定向到首页
        //    如果登录失败，回到登录页，并且给请求域添加错误信息返回给登录页接收
        if(dbUser != null) {
            req.getSession().setAttribute("dbUser", dbUser);
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            req.setAttribute("loginMsg", "账号或密码错误");
            this.processTemplate("user/login", req, resp);
        }
    }

    // 注册
    public void register(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        // 1. 获取前端数据
        Map<String, String[]> parameterMap = req.getParameterMap();

        // 2. 封装成 user 对象
        User user = new User();
        BeanUtils.populate(user, parameterMap);

        // 3. 调用 userService.register(user) 方法处理
        boolean flag = userService.register(user);

        // 4. 注册成功重定向到登录页
        //    注册失败会到注册页
        if(flag) {
            resp.sendRedirect("user?method=login");
        } else {
            this.processTemplate("register", req, resp);
        }
    }

    // 退出登录
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("dbUser");
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
