package com.atguigu.bookstore.service;

import com.atguigu.bookstore.domain.Account;
import com.dao.AccountDAO;
import com.dao.AccountDAOImpl;

public class AccountService {

    private AccountDAO accountDAO=new AccountDAOImpl();
    public Account getAccount(int accountId){
        return accountDAO.get(accountId);
    }
}
