package com.atguigu.services;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.User;

import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/14 10:51 AM Sunday
 * @Description:
 */
public interface OrderService {
    String addOrder(Cart cart, User user);

    List<Order> findOrderByUserId(Integer id);
}
