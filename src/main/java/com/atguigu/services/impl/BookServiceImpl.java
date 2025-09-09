package com.atguigu.services.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.services.BookService;

import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/6 10:09 AM Saturday
 * @Description:
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    @Override
    public Book findBookById(int id) {
        return bookDao.findBookById(id);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }
}
