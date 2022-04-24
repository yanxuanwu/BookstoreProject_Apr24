package com.servlet;

import com.atguigu.bookstore.domain.User;
import com.atguigu.bookstore.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UsersServlet", value = "/UsersServlet")
public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    private UserService userService= new UserService();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        User user = userService.getUserWithTrades(username);

        if(user==null){
            response.sendRedirect(request.getServletPath()+"/error-1.jsp");

        }
        request.setAttribute("user",user);
        request.getRequestDispatcher("/WEB-INF/pages/trade.jsp").forward(request,response);

    }
}
