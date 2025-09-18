package com.atguigu.services.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.services.OrderService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @Author: ljg
 * @Date: 2025/9/14 10:51 AM Sunday
 * @Description:
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Override
    public String addOrder(Cart cart, User dbUser) {
        // 添加订单
        Order order = new Order();
        String sequence = UUID.randomUUID().toString();
        order.setSequence(sequence);
        order.setStatus(0);
        order.setTotalCount(cart.getTotalCount());
        order.setTotalAmount(cart.getTotalAmount());
        order.setUserId(dbUser.getId());
        // 订单入库操作
        orderDao.addOrder(order);

        // 查询插入的订单 id
        Order dbOrder = orderDao.findOrderBySequence(sequence);

        // 订单项入库
        Collection<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(cartItem.getBook().getId());
            orderItem.setCount(cartItem.getCount());
            orderItem.setAmount(cartItem.getAmount());
            orderItem.setOrderId(dbOrder.getId());
            orderItemDao.addOrderItem(orderItem);
        }

        return sequence;
    }

    @Override
    public List<Order> findOrderByUserId(Integer id) {
        return orderDao.findOrderByUserId(id);
    }
}
