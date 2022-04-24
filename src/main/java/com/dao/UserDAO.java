package com.dao;

import com.atguigu.bookstore.domain.User;

public interface UserDAO {
    public abstract User getUser(String username);

}
