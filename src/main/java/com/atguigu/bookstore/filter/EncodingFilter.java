package com.atguigu.bookstore.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private FilterConfig filterConfig= null;
    public void init(FilterConfig fconfig) throws ServletException {
        this.filterConfig=fconfig;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String encoding=filterConfig.getServletContext().getInitParameter("encoding");
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }
}
