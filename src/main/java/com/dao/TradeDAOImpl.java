package com.dao;

import com.atguigu.bookstore.domain.Trade;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class TradeDAOImpl extends  BaseDAO<Trade> implements  TradeDAO{
    @Override
    public void insert(Trade trade) {
        String sql="insert into trade (userid, tradetime) VALUEs " +"(?,?)";
        long tradeId= insert(sql,trade.getUserId(), trade.getTradeTime());
        trade.setTradeId((int)tradeId);

    }

    @Override
    public Set<Trade> getTradeWithUserId(Integer userId) {
        String sql="select tradeId,userId,tradeTime FROM trade "+"where userId=?";
        return new HashSet(queryForList(sql,userId));
    }
}
