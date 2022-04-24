package com.dao;

import com.atguigu.bookstore.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO{
    @Override
    public User getUser(String username) {
        String sql="SELECT  userId, username, accountId "+"FROM userinfo where username=?";

        return query(sql,username);
    }
}
