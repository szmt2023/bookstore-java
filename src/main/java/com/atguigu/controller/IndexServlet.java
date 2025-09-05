package com.atguigu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ljg
 * @Date: 2025/9/5 3:07 PM Friday
 * @Description:
 */
@WebServlet("/index.html")
public class IndexServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processTemplate("index", req, resp);
    }
}
