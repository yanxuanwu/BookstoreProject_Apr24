package com.atguigu.bookstore.domain;

import java.sql.Date;
        import java.util.LinkedHashSet;
        import java.util.Set;

public class Trade {

    private Integer tradeId;

    private Date tradeTime;

    private Set<TradeItem> items = new LinkedHashSet<TradeItem>();

    public void setItems(Set<TradeItem> items) {
        this.items = items;
    }

    public Set<TradeItem> getItems() {
        return items;
    }

    private Integer userId;

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Override
    public String toString() {
        return "Trade [tradeId=" + tradeId + ", tradeTime=" + tradeTime
                + ", userId=" + userId + "]";
    }
}