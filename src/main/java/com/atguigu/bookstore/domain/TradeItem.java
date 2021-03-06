package com.atguigu.bookstore.domain;

import java.util.Collection;
import java.util.Set;


public class TradeItem {

    private Integer tradeItemId;

    private Book book;

    private int quantity;

    private Integer bookId;

    private Integer tradeId;

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public Integer getTradeItemId() {
        return tradeItemId;
    }

    public void setTradeItemId(Integer tradeItemId) {
        this.tradeItemId = tradeItemId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public TradeItem(Integer tradeItemId, Integer bookId, int quantity,
                     Integer tradeId) {
        super();
        this.tradeItemId = tradeItemId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.tradeId = tradeId;
    }

    public TradeItem() {
        // TODO Auto-generated constructor stub

    }

    @Override
    public String toString() {
        return "TradeItem [tradeItemId=" + tradeItemId + ", quantity="
                + quantity + ", bookId=" + bookId + "]";
    }



}