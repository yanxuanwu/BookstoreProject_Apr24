package com.dao;

import com.atguigu.bookstore.domain.Trade;

import java.util.Set;

public interface TradeDAO {
    /*
    insert trade
     */
    public abstract void insert(Trade trade);

    public abstract Set<Trade> getTradeWithUserId(Integer userId);
}
