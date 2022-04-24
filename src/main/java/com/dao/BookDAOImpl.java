package com.dao;

import com.atguigu.bookstore.domain.Book;
import com.atguigu.bookstore.domain.ShoppingCartItem;
import com.com.atguigu.bookstore.CriteriaBook;
import com.com.atguigu.bookstore.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public Book getBook(int id){
        String sql="select id,author,title,price,publishingDate,"+"salesAmount, storeNumber,remark FROM mybooks WHERE id=?";
        return query(sql,id);

    }

    @Override
    public Page<Book> getPage(CriteriaBook cb){
        Page page= new Page<>(cb.getPageNo());
        page.setTotalItemNumber(getTotalBookNumber(cb));
        cb.setPageNo(page.getPageNo());
        page.setList(getPageList(cb,3));
        return page;

    }

    @Override
    public long getTotalBookNumber(CriteriaBook cb){
        String sql="SELECT count(id) FROM mybooks WHERE price >=? AND price<=?";
        return getSingleVal(sql,cb.getMinPrice(),cb.getMaxPrice());
    }

    @Override
    public List<Book> getPageList(CriteriaBook cb, int pageSize){
        String sql="SELECT id,author,title,price,publishingDate,"+"salesAmount, storeNumber,remark FROM mybooks WHERE price>=? AND price<=?  LIMIT ?,?";
        return queryForList(sql,cb.getMinPrice(),cb.getMaxPrice(),(cb.getPageNo()-1)*3,pageSize);

    }

    @Override
    public int getStoreNumber(Integer id) {
        String sql="SELECT storeNumber FROM mybooks WHERE id =?";
        return getSingleVal(sql,id);
    }

    @Override
    public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
        String sql="update mybooks set salesAmount = salesAmount+?, "+ "storeNumber=storeNumber-? "+"where id=?";
        Object[][] params=null;
        params=new Object[items.size()][3];
        List<ShoppingCartItem> scis=new ArrayList<>(items);
        for (int i = 0; i < items.size(); i++) {
            params[i][0]=scis.get(i).getQuantity();
            params[i][1]=scis.get(i).getQuantity();
            params[i][2]=scis.get(i).getBook().getId();

        }
        batch(sql,params);

    }


}
