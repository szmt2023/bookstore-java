package com.atguigu.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: ljg
 * @Date: 2025/9/10 9:51 PM Wednesday
 * @Description:
 */

public class CartItem {
    private Book book;
    // 总额
    private Double amount;
    // 数量
    private Long count;

    public CartItem(Book book, Long count) {
        this.book = book;
        this.count = count;
        BigDecimal priceDec = new BigDecimal(book.getPrice() + "");
        BigDecimal countDec = new BigDecimal(count + "");
        this.amount = priceDec.multiply(countDec).doubleValue();
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        BigDecimal priceDec = new BigDecimal(book.getPrice() + "");
        BigDecimal countDec = new BigDecimal(count + "");
        this.amount = priceDec.multiply(countDec).doubleValue();
    }

    public Double getAmount() {
        return this.amount;
    }


    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
        BigDecimal priceDec = new BigDecimal(this.book.getPrice() + "");
        BigDecimal countDec = new BigDecimal(this.count + "");
        this.amount = priceDec.multiply(countDec).doubleValue();
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", amount=" + amount +
                ", count=" + count +
                '}';
    }
}
