package com.dao;

import com.atguigu.bookstore.domain.TradeItem;

import java.util.*;

public class TradeItemDAOImpl extends BaseDAO<TradeItem> implements TradeItemDAO{
    @Override
    public void batchSave(Collection<TradeItem> items) {
        String sql="Insert into tradeitem(bookid,quantity, tradeid) " +"VALUES(?,?,?)";
        Object[][]params= new Object[items.size()][3];

        List<TradeItem> tradeItems= new ArrayList<>(items);
        for (int i = 0; i < tradeItems.size(); i++) {
            params[i][0]=tradeItems.get(i).getBookId();
            params[i][1]=tradeItems.get(i).getQuantity();
            params[i][2]=tradeItems.get(i).getTradeId();
        }

        batch(sql,params);

    }

    @Override
    public Set<TradeItem> getTradeItemWithTradeId(Integer tradeId) {
        String sql ="select itemId tradeItemId,bookId,quantity,tradeId FROM tradeitem where tradeid=? order by tradeTime DESC";
        return new LinkedHashSet(queryForList(sql,tradeId));
    }
}
