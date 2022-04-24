package com.test;

import com.atguigu.bookstore.domain.TradeItem;
import com.dao.TradeDAO;
import com.dao.TradeDAOImpl;
import com.dao.TradeItemDAO;
import com.dao.TradeItemDAOImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TradeItemDAOImplTest {
    private TradeItemDAO tradeItemDAO= new TradeItemDAOImpl();


    @Test
    void batchSave() {
        Collection<TradeItem> items = new ArrayList<>();
        items.add(new TradeItem(null,1,10,16));
        items.add(new TradeItem(null,2,20,16));
        items.add(new TradeItem(null,3,30,16));
        tradeItemDAO.batchSave(items);

    }

    @Test
    void getTradeItemWithTradeId() {
        Set<TradeItem> items= tradeItemDAO.getTradeItemWithTradeId(25);
        System.out.println(items);

    }
}