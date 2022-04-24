package com.test;

import com.atguigu.bookstore.domain.Account;
import com.dao.AccountDAO;
import com.dao.AccountDAOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOImplTest {
    AccountDAO accountDao= new AccountDAOImpl();


    @Test
    void get() {
        Account account= accountDao.get(1);
        System.out.println(account.getBalance());
    }

    @Test
    void updateBalance() {
        accountDao.updateBalance(1,50);
    }
}