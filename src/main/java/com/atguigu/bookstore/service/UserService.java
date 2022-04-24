package com.atguigu.bookstore.service;

import com.atguigu.bookstore.domain.Trade;
import com.atguigu.bookstore.domain.TradeItem;
import com.atguigu.bookstore.domain.User;
import com.dao.*;

import java.util.Set;

public class UserService {
    private UserDAO userDAO= new UserDAOImpl();
    public User getUserByUserName(String username){
        return userDAO.getUser(username);
    }

    private TradeDAO tradeDAO= new TradeDAOImpl();
    private TradeItemDAO tradeItemDAO= new TradeItemDAOImpl();
    private BookDAO bookDAO= new BookDAOImpl();

    public User getUserWithTrades(String username){
        User user=userDAO.getUser(username);
        if(user==null){
            return null;
        }
        int userId=user.getUserId();
        Set<Trade> trades= tradeDAO.getTradeWithUserId(userId);
        if(trades!=null){
            for(Trade trade: trades){
                int tradeId=trade.getTradeId();
                Set<TradeItem> items = tradeItemDAO.getTradeItemWithTradeId(tradeId);

                if (items!=null){
                    for(TradeItem item:items){
                        item.setBook(bookDAO.getBook(item.getBookId()));
                    }
                    trade.setItems(items);
                }
          }
        }
        user.setTrades(trades);

        return user;
    }
}
