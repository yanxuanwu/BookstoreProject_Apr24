package com.atguigu.bookstore.service;

import com.atguigu.bookstore.domain.*;
import com.com.atguigu.bookstore.CriteriaBook;
import com.com.atguigu.bookstore.Page;
import com.dao.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public class BookService {
    private BookDAO bookDAO= new BookDAOImpl();
    public Page<Book> getPage(CriteriaBook criteriaBook){
        return bookDAO.getPage(criteriaBook);

    }

    public Book getBook(int id){
        return bookDAO.getBook(id);
    }

    public boolean addToCart(int id, ShoppingCart sc){
        Book book= bookDAO.getBook(id);
        if(book!=null){
            sc.addBook(book);
            return true;
        }
        else{
            return false;
        }
    }

    public void removeItemFromShoppingCart(ShoppingCart sc, int id) {
        sc.removeItem(id);

    }

    public void clearShoppingCart(ShoppingCart sc) {
        sc.clear();
    }

    public void updateItemQuantity(ShoppingCart sc, int id, int quantity) {
        sc.updateItemQuantity(id,quantity);
    }

    private AccountDAO accountDAO= new AccountDAOImpl();
    private TradeDAO tradeDAO= new TradeDAOImpl();
    private  UserDAO userDAO= new UserDAOImpl();
    private TradeItemDAO tradeItemDAO= new TradeItemDAOImpl();
    public void cash(ShoppingCart shoppingCart, String username, String accountId){
        bookDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());


        accountDAO.updateBalance(Integer.parseInt(accountId),shoppingCart.getTotalMoney());

        //insert record to trade table
        Trade trade= new Trade();
        trade.setTradeTime(new Date(new java.util.Date().getTime()));
        trade.setUserId(userDAO.getUser(username).getUserId());
        tradeDAO.insert(trade);


        //insert n records into tradeitem tables

        Collection<TradeItem> items= new ArrayList<>();
        for (ShoppingCartItem sci: shoppingCart.getItems()){
            TradeItem tradeItem= new TradeItem();
            tradeItem.setBookId(sci.getBook().getId());
            tradeItem.setQuantity(sci.getQuantity());
            tradeItem.setTradeId(trade.getTradeId());
            items.add(tradeItem);
        }


        tradeItemDAO.batchSave(items);

        shoppingCart.clear();
    }


}
