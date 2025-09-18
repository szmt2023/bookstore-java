package com.atguigu.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author: ljg
 * @Date: 2025/9/12 11:24 AM Friday
 * @Description:
 */
//@WebFilter("/*")
public class RequestLogFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        long startTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;

        logger.info("请求: {} {} | 耗时: {}ms | IP: {}",
                request.getMethod(), request.getRequestURI(), duration, request.getRemoteAddr());
    }

    @Override
    public void destroy() {

    }
}
