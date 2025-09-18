package com.atguigu.pojo;

import lombok.Data;

/**
 * @Author: ljg
 * @Date: 2025/9/14 10:31 AM Sunday
 * @Description: 订单项
 */
@Data
public class OrderItem {
    // 订单项 id
    private Integer id;
    // 关联的图书 id
    private Integer bookId;
    // 订单项图书的数量
    private Long count;
    // 订单项的价格
    private Double amount;
    // 关联的订单 id
    private Integer orderId;

}
