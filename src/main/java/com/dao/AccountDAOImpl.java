package com.dao;

import com.atguigu.bookstore.domain.Account;
import com.db.JDBCUtils;

import java.sql.Connection;

public class AccountDAOImpl extends BaseDAO<Account> implements AccountDAO{
    @Override
    public Account get(Integer accountId) {

        String sql="SELECT accountId, balance FROM account where accountId=?";
        return query(sql,accountId);

    }

    @Override
    public void updateBalance(Integer accountId, float amount) {
        String sql="Update account SET balance=balance-? where accountId=?";
        update(sql,amount,accountId);

    }
}
