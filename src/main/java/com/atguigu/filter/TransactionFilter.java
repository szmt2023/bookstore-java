package com.atguigu.filter;

import com.atguigu.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: ljg
 * @Date: 2025/9/17 10:03 AM Wednesday
 * @Description:
 */

@WebServlet("/*")
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *  如何实现事务控制：
     *    业务说明：
     *      1. 当用户执行业务操作时，如果其中代码报错，才会进行事务的回滚
     *      2. 当用户操作业务正确，事务应该提交
     *      3. 过滤器必须接收到别人的异常信息
     *     规则说明：业务执行过程中，不能随意捕获异常
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取数据库链接
        Connection conn = JDBCUtils.getConnection();
        // 将自动提交改为手动提交
        try {
            conn.setAutoCommit(false);
            // 过滤器应该放行请求，让他执行
            filterChain.doFilter(servletRequest, servletResponse);
            // 如果程序没有错，就要进行事务提交
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            // 不管结果怎么样，都要释放连接，交还线程池
            JDBCUtils.releaseConnection();
        }
    }

    @Override
    public void destroy() {

    }
}
