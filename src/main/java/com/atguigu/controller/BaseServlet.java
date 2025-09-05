package com.atguigu.controller;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ljg
 * @Date: 2025/9/1 8:37 AM Monday
 * @Description:
 */
public class BaseServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        ServletContext ctx = this.getServletContext();
        // 1. 创建模版解析器
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(ctx);
        // 设置解析路径
        String prefix = ctx.getInitParameter("view-prefix");
        String suffix = ctx.getInitParameter("view-suffix");
        templateResolver.setPrefix(prefix);
        templateResolver.setSuffix(suffix);
        // 设置解析模版
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // 设置缓存
        templateResolver.setCacheable(true);
        templateResolver.setCacheTTLMs(60000L);
        // 设置编码
        templateResolver.setCharacterEncoding("UTF-8");

        // 创建模版引擎
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");

        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        templateEngine.process(templateName, webContext, resp.getWriter());
    }
}
