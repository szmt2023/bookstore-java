package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.OrderItem;








/**
 * @Author: ljg
 * @Date: 2025/9/14 10:38 AM Sunday
 * @Description:
 */
public class OrderItemDaoImpl extends BaseDaoImpl implements OrderItemDao {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO tb_order_item(id, book_id, count, amount, order_id) VALUES(null, ?, ?, ?,?)";
        this.update(sql, orderItem.getBookId(), orderItem.getCount(), orderItem.getAmount(), orderItem.getOrderId());
    }
}
