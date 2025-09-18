package com.atguigu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;
}
