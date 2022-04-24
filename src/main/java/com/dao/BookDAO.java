package com.dao;

import com.atguigu.bookstore.domain.Book;
import com.atguigu.bookstore.domain.ShoppingCartItem;
import com.com.atguigu.bookstore.CriteriaBook;
import com.com.atguigu.bookstore.Page;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public interface BookDAO {
    public abstract Book getBook(int id);
    public abstract Page<Book> getPage(CriteriaBook cb);
    public abstract long getTotalBookNumber(CriteriaBook cb);
    public abstract List<Book> getPageList(CriteriaBook cb, int pageSize);
    public abstract int getStoreNumber(Integer id);

    public abstract void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);




}
