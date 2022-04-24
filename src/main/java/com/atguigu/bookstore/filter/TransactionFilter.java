package com.atguigu.bookstore.filter;

import com.db.JDBCUtils;
import com.utils.ConnectionContext;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(filterName = "TransactionFilter")
public class TransactionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Connection connection=null;
        try{
            //get connection
            connection = JDBCUtils.getConnection();
            //start transaction
            connection.setAutoCommit(false);
            //use Threadlocal to combine connection and thread
            ConnectionContext.getInstance().bind(connection);
            //transfer request to target servlet
            chain.doFilter(request, response);

            //submit transaction
            connection.commit();

        }
        catch (Exception e){
            e.printStackTrace();

            //roll back
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            HttpServletResponse resp=(HttpServletResponse) response;
            HttpServletRequest req=(HttpServletRequest) request;
            resp.sendRedirect(req.getContextPath()+"/error-1.jsp");
        }finally {
            //do not combine anymore
            ConnectionContext.getInstance().remove();

            JDBCUtils.release(connection);
        }

        }
}
