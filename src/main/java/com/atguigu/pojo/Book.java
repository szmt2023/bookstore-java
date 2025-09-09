package com.atguigu.pojo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: ljg
 * @Date: 2025/9/6 10:02 AM Saturday
 * @Description:
 */
@Data
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Double price;
    private Integer sales;
    private Integer stock;
    private String imgPath;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
