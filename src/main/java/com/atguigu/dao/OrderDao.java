package com.atguigu.dao;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;

import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/14 10:36 AM Sunday
 * @Description:
 */
public interface OrderDao {
    void addOrder(Order order);
    Order findOrderBySequence(String sequence);

    List<Order> findOrderByUserId(Integer id);
}
