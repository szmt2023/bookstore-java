package com.atguigu.controller;

import com.atguigu.constants.Constants;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.User;
import com.atguigu.services.BookService;
import com.atguigu.services.OrderService;
import com.atguigu.services.impl.BookServiceImpl;
import com.atguigu.services.impl.OrderServiceImpl;
import com.atguigu.vo.SysResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: ljg
 * @Date: 2025/9/10 11:14 AM Wednesday
 * @Description:
 */
@WebServlet("/cart")
public class CartServlet extends BaseServlet{
    private static ObjectMapper MAPPER = new ObjectMapper();
    private BookService bookService = new BookServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    static {
        // 注册 Java8 的时间模块
        MAPPER.registerModule(new JavaTimeModule());
        // 避免将 LocalDateTime 序列化为数组
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy" +
                "-MM-dd HH:mm:ss")));
        MAPPER.registerModule(simpleModule);
    }

    public void toCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.processTemplate("cart/cart", req, resp);
    }


    /**
     * 接口名称：加入购物接口
     * url：/cart?method=addToCart
     * method：GET
     * query：bookId
     * 返回购物车总数量
     *
     */
    public void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("bookId");
        if(id == null || id.isEmpty()) {
            throw new RuntimeException("bookId 输入有误");
        }

        Book book = bookService.findBookById(Integer.parseInt(id));

        if(book == null) {
            String jsonStr = MAPPER.writeValueAsString(SysResult.fail("书籍不存在", null));
        }

        // 获取购物车信息（一般都是存放到非关系性数据库，例如 mognodb，现在先存放到 session 中，模拟）
        Cart cart = this.getCart(req, resp);
        // 把 book 加入购物车
        cart.addToCart(book);

        // 返回最新的总数量
        Long totalCount = cart.getTotalCount();

        SysResult sysResult = SysResult.success("加入购物车成功", totalCount);
        String jsonStr = MAPPER.writeValueAsString(sysResult);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }

    /**
     * 接口名称：加入购物接口
     * url：/cart?method=getCartList
     * method：GET
     */
    public void getCartList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = this.getCart(req, resp);
        String jsonStr = MAPPER.writeValueAsString(SysResult.success("获取购物车列表成功", cart));
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }

    /**
     * 接口名称：更新数量
     * url：/cart?method=getCartList
     * method：GET
     */
    public void updateQuantity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookId = req.getParameter("bookId");
        String count = req.getParameter("count");

        // 获取 session 中的购物车信息 / 一般获取的是 mongodb 中的，这里用 session 替代
        Cart cart = this.getCart(req, resp);
        cart.updateQuantity(Integer.parseInt(bookId), Long.parseLong(count));

        String jsonStr = MAPPER.writeValueAsString(SysResult.success());
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }

    // 清空购物车
    public void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 删除 session 中的购物车数据
        req.getSession().removeAttribute(Constants.SESSION_CART_KEY);

        String jsonStr = MAPPER.writeValueAsString(SysResult.success());
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }

    // 根据 bookId 从购物车中移除指定的 cartItem
    public void clearCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        Cart cart = getCart(req, resp);
        cart.clearByBookId(bookId);
        String jsonStr = MAPPER.writeValueAsString(SysResult.success());
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }




    // 获取购物车信息
    private Cart getCart(HttpServletRequest req, HttpServletResponse resp) {
        // session 存放的购物车结构：{}
        Cart cart = (Cart) req.getSession().getAttribute(Constants.SESSION_CART_KEY);
        if(cart == null) {
            // 创建新的购物车对象(此时为空到购物车)，并把它保存到 session 中
            cart = new Cart();
            req.getSession().setAttribute(Constants.SESSION_CART_KEY, cart);
        }
        return cart;
    }

}
