package com.atguigu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: ljg
 * @Date: 2025/9/14 10:26 AM Sunday
 * @Description: 订单
 */
@Data
public class Order implements Serializable {
    // 订单 ID
    private Integer id;
    // 订单编号（唯一不重复）
    private String sequence;
    // 订单创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    // 订单总数
    private Long totalCount;
    //订单总价
    private Double totalAmount;
    // 订单状态
    private Integer status;
    // 用户 id
    private Integer userId;
}
