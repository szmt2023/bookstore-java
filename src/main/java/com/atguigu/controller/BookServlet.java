package com.atguigu.controller;

import com.atguigu.pojo.Book;
import com.atguigu.services.BookService;
import com.atguigu.services.impl.BookServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Author: ljg
 * @Date: 2025/9/6 7:26 AM Saturday
 * @Description:
 */
@WebServlet("/book")
public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    // 书城管理后台
    public void toAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.processTemplate("manager/manager", req, resp);
    }

    /**
     * <a href="book?method=toAddBook">添加图书</a>
     */
    public void toAddBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.processTemplate("manager/book_add", req, resp);
    }

    // 获取图书列表

    public void getBookList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Book> list = bookService.getBookList();
        req.setAttribute("bookList", list);
        this.processTemplate("manager/book_manager", req, resp);
    }


    /**
     *  <a th:href="@{/book(method=toEditBook,id=${book.id})}">修改</a>
     */
    public void toEditBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookService.findBookById(id);

        req.setAttribute("book", book);
        this.processTemplate("manager/book_edit", req, resp);
    }


    /**
     * <form action="book?method=addBook" method="post">
     */
    public void addBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = new Book();
        BeanUtils.populate(book, parameterMap);
        bookService.addBook(book);
        resp.sendRedirect(req.getContextPath() + "/book?method=getBookList");
    }

    /**
     *  <form action="book?method=updateBook" method="post">
     */
    public void updateBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = new Book();
        BeanUtils.populate(book, parameterMap);
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/book?method=getBookList");
    }


    /**
     * <a th:href="@{/book(method=deleteBook,id=${book.id})}" class="del">删除</a>
     */
    public void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookService.deleteBookById(id);

        resp.sendRedirect(req.getContextPath() + "/book?method=getBookList");
    }
}
