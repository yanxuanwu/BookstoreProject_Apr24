package com.test;

import com.atguigu.bookstore.domain.Book;
import com.dao.BaseDAO;
import com.dao.BookDAOImpl;
import org.junit.Test;


import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseDAOTest {
    private BaseDAO baseDAO=new BaseDAO();
    private BookDAOImpl bookDaoImpl= new BookDAOImpl();

    @Test
    void testInsert() {
        String sql="insert into trade(userid,tradetime) values(?,?)";
        long id=baseDAO.insert(sql,1,new Date(new java.util.Date().getTime()));
        System.out.println(id);
    }

    @Test
    void testupdate() {
        String sql="update mybooks set salesamount=10";
        bookDaoImpl.update(sql,10,4);
    }

    @Test
    void testquery() {
        String sql="Select id, author, title,price,publishingDate,"+"salesAmount,storeNumber,remark FROM mybooks where id=?";
        Book book = bookDaoImpl.query(sql,4);
    }

    @Test
    void queryForList() {
        String sql="select id,author, title,price,publishingDate,"+"salesAmount,storeNumber,remark FROM mybooks where id<?";
        List<Book> books=bookDaoImpl.queryForList(sql,4);
        System.out.println(books);
    }

    @Test
    void testgetSingleVal() {
        String sql="select count(id) From mybooks";
        long count=bookDaoImpl.getSingleVal(sql);
        System.out.println(count);
    }

    @Test
    void testbatch() {
        String sql="update mybook set salesAmount=?, storeNumber=?"+"where id=?";
        bookDaoImpl.batch(sql, new Object[]{1,1,1}, new Object[]{2,2,2}, new Object[]{3,3,3});
    }
    
}