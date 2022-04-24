package com.dao;

import com.atguigu.bookstore.domain.Book;
import com.atguigu.bookstore.domain.ShoppingCartItem;
import com.com.atguigu.bookstore.CriteriaBook;
import com.com.atguigu.bookstore.Page;
import com.db.JDBCUtils;
import com.utils.ConnectionContext;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOTest {
    private  BookDAO bookDao= new BookDAOImpl();

    @Test
    void getBook() {
        Connection connection= JDBCUtils.getConnection();
        ConnectionContext.getInstance().bind(connection);

        Book book =bookDao.getBook(5);
        System.out.println(book);

    }

    @Test
    void getPage() {
        CriteriaBook cb= new CriteriaBook(50,60,90);
        Page<Book> page=bookDao.getPage(cb);
        System.out.println("PageNo"+page.getPageNo());
        System.out.println("totalPage Number:"+page.getTotalPageNumber());
        System.out.println("list"+page.getList());
        System.out.println("prevPage"+page.getPrevPage());
        System.out.println("nextPage"+page.getNextPage());
    }

    @Test
    void getTotalBookNumber() {
    }

    @Test
    void getPageList() {
    }

    @Test
    void getStoreNumber() {
    }

    @Test
    public void testBatchUpdateStoreNumberAndSalesAmount(){
        Collection<ShoppingCartItem> items= new ArrayList<>();
        Book book= bookDao.getBook(1);
        ShoppingCartItem sci= new ShoppingCartItem(book);
        sci.setQuantity(10);
        items.add(sci);

        book= bookDao.getBook(2);
        sci= new ShoppingCartItem(book);
        sci.setQuantity(11);
        items.add(sci);

        book= bookDao.getBook(3);
        sci= new ShoppingCartItem(book);
        sci.setQuantity(12);
        items.add(sci);

        book= bookDao.getBook(4);
        sci= new ShoppingCartItem(book);
        sci.setQuantity(14);
        items.add(sci);

        bookDao.batchUpdateStoreNumberAndSalesAmount(items);
    }
}