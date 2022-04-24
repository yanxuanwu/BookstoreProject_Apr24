package com.dao;

import com.atguigu.bookstore.domain.TradeItem;

import java.util.Collection;
import java.util.Set;

public interface TradeItemDAO {
    public abstract void batchSave(Collection<TradeItem> items);

    public Set<TradeItem> getTradeItemWithTradeId(Integer tradeId);

}
