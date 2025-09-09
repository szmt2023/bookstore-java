package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/6 10:10 AM Saturday
 * @Description:
 */
public class BookDaoImpl extends BaseDaoImpl implements BookDao {
    @Override
    public List<Book> getBookList() {
        String sql = "SELECT id, author, title, price, sales, stock, img_path imgPath, " +
                "create_at createAt, update_at updateAt FROM tb_books";
        return this.getList(Book.class, sql);
    }

    @Override
    public Book findBookById(int id) {
        String sql = "SELECT id, author, title, price, sales, stock, img_path imgPath, " +
                "create_at createAt, update_at updateAt FROM tb_books WHERE id = ?";
        return this.getBean(Book.class, sql, id);
    }

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO tb_books(author, title, price, sales, stock, img_path) VALUES(?,?,?,?,?,?)";
        this.update(sql, book.getAuthor(), book.getTitle(), book.getPrice(), book.getSales(), book.getStock(),
                book.getImgPath());
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE tb_books SET author=?, title=?, price=?, sales=?, stock=?, img_path=? WHERE id = ?";
        this.update(sql, book.getAuthor(), book.getTitle(), book.getPrice(), book.getSales(), book.getStock(),
                book.getImgPath(), book.getId());
    }

    @Override
    public void deleteBookById(int id) {
        String sql = "DELETE FROM tb_books WHERE id = ?";
        this.update(sql, id);
    }
}
