package com.atguigu.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: ljg
 * @Date: 2025/9/5 3:04 PM Friday
 * @Description:
 */
public abstract class BaseServlet extends ViewBaseServlet{
    private final String METHOD = "method";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter(METHOD);
        Class<? extends BaseServlet> clazz = this.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            String name = declaredMethod.getName();
            if(name.equals(methodName)) {
                declaredMethod.setAccessible(true);
                try {
                    declaredMethod.invoke(this, req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
