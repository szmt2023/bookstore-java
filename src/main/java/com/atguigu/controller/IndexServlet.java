package com.atguigu.controller;

import com.atguigu.pojo.Book;
import com.atguigu.services.BookService;
import com.atguigu.services.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/5 3:07 PM Friday
 * @Description:
 */
@WebServlet("/index.html")
public class IndexServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = bookService.getBookList();

        req.setAttribute("bookList", bookList);
        this.processTemplate("index", req, resp);
    }
}
