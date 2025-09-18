package com.atguigu.controller;

import com.atguigu.constants.Constants;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.User;
import com.atguigu.services.OrderService;
import com.atguigu.services.impl.OrderServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @Author: ljg
 * @Date: 2025/9/14 10:53 AM Sunday
 * @Description:
 */
@WebServlet("/order")
public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    // 结账
    public void toCheckout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 添加订单
        // 1. 获取 session 中购物车信息
        Cart cart = (Cart) req.getSession().getAttribute(Constants.SESSION_CART_KEY);
        User dbUser =(User)req.getSession().getAttribute(Constants.SESSION_USER_KEY);
        // 2. 利用业务层实现订单入库操作，并且返回订单编号
        String sequence = orderService.addOrder(cart, dbUser);

        // 3. 利用 request 域，将订单号保存到域中
        req.setAttribute("orderSequence", sequence);

        // 4. 清空购物车信息，删除 session 的数据
        req.getSession().removeAttribute(Constants.SESSION_CART_KEY);

        this.processTemplate("cart/checkout", req, resp);
    }


    public void findOrderByUserId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User dbUser = (User) req.getSession().getAttribute(Constants.SESSION_USER_KEY);
        List<Order> list = orderService.findOrderByUserId(dbUser.getId());
        req.setAttribute("list", list);
        this.processTemplate("order/order", req, resp);
    }

}
