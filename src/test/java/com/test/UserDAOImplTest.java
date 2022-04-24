package com.test;

import com.atguigu.bookstore.domain.User;
import com.dao.UserDAO;
import com.dao.UserDAOImpl;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {
    private UserDAO userDAO = new UserDAOImpl();
    @Test
    public void testGetUser() {
        User user= userDAO.getUser("AAA");
        System.out.println(user);
    }
}