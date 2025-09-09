package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/6 10:10 AM Saturday
 * @Description:
 */
public interface BookDao {
    List<Book> getBookList();

    Book findBookById(int id);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBookById(int id);
}
