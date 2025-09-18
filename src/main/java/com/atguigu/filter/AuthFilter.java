package com.atguigu.filter;


import com.atguigu.constants.Constants;
import com.atguigu.pojo.User;
import com.atguigu.vo.SysResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: ljg
 * @Date: 2025/9/12 9:56 AM Friday
 * @Description:
 */
@WebFilter({"/cart/*", "/order/*", "/book/*"})
public class AuthFilter implements Filter {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 判断是否为 ajax 请求
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute(Constants.SESSION_USER_KEY) == null) {
            if(isAjax) {
                String jsonStr = MAPPER.writeValueAsString(SysResult.fail());
                response.getWriter().write(jsonStr);
            } else {
                response.sendRedirect(request.getContextPath() + "/user?method=toLogin");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
