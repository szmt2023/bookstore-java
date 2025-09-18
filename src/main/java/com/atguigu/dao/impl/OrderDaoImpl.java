package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.User;

import java.util.List;
import java.util.UUID;

/**
 * @Author: ljg
 * @Date: 2025/9/14 10:37 AM Sunday
 * @Description:
 */
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    @Override
    public Order findOrderBySequence(String sequence) {
        String sql = "SELECT id, sequence, create_time createTime, total_count totalCount, total_amount totalAmount, " +
                "status, user_id userId " +
                "FROM tb_order WHERE sequence = ?";
        Order order = this.getBean(Order.class, sql, sequence);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        String sql = "INSERT INTO tb_order(sequence, total_count, total_amount, user_id) VALUES(?, ?, ?, ?)";
        int update = this.update(sql, order.getSequence(), order.getTotalCount(), order.getTotalAmount(),
                order.getUserId());
    }

    @Override
    public List<Order> findOrderByUserId(Integer id) {
        String sql = "SELECT id, sequence, create_time createTime, total_count totalCount, " +
                "total_amount totalAmount, status, user_id userId FROM tb_order WHERE user_id = ?";

        return this.getList(Order.class, sql, id);
    }
}
