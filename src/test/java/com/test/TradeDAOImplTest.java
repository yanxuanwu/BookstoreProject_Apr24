package com.test;

import com.atguigu.bookstore.domain.Trade;
import com.dao.TradeDAO;
import com.dao.TradeDAOImpl;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TradeDAOImplTest {

    private TradeDAO tradeDAO=new TradeDAOImpl();
    @Test
    public void  testInsertTrade(){
        Trade trade= new Trade();
        trade.setUserId(3);
        trade.setTradeTime(new Date(new java.util.Date().getTime()));

        tradeDAO.insert(trade);

    }

    @Test
    public void testGetTradeWithUserId(){
        Set<Trade> trades =tradeDAO.getTradeWithUserId(1);
        System.out.println(trades);
    }

}