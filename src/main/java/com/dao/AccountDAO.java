package com.dao;

import com.atguigu.bookstore.domain.Account;

public interface AccountDAO {
    public abstract Account get(Integer accountId);
    public abstract void updateBalance(Integer accountId, float amount);

}
