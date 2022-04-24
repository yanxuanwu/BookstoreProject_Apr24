package com.servlet;

import com.atguigu.bookstore.domain.*;
import com.atguigu.bookstore.service.AccountService;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.UserService;
import com.com.atguigu.bookstore.CriteriaBook;
import com.com.atguigu.bookstore.Page;
import com.google.gson.Gson;
import com.utils.BookStoreWebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    private  BookService bookService= new BookService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName=request.getParameter("method");
        try {
            Method method= getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    private UserService userService= new UserService();

    protected void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String accountId=request.getParameter("accountId");
        StringBuffer errors= validateFormField(username,accountId);

        if(errors.toString().equals("")){
            errors=validateUser(username,accountId);

            if(errors.toString().equals("")){
                errors=validateBookStoreNumber(request);

                if(errors.toString().equals("")){
                    errors=validateBalance(request,accountId);
                }
            }
        }


        if(!errors.toString().equals("")){
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request,response);
            return;
        }

        bookService.cash(BookStoreWebUtils.getShoppingCart(request),username,accountId);
        response.sendRedirect(request.getContextPath()+"/success.jsp");
    }

    private AccountService accountService= new AccountService();

    public StringBuffer validateBalance(HttpServletRequest request,String accountId){
        StringBuffer errors= new StringBuffer("");
        ShoppingCart cart = BookStoreWebUtils.getShoppingCart(request);
        Account account= accountService.getAccount(Integer.parseInt(accountId));
        if(cart.getTotalMoney()>account.getBalance()){
            errors.append("balance is not enough");

        }
        return errors;
    }

    public StringBuffer validateBookStoreNumber(HttpServletRequest request){
        StringBuffer errors= new StringBuffer("");
        ShoppingCart cart= BookStoreWebUtils.getShoppingCart(request);

        for(ShoppingCartItem sci:cart.getItems()){
            int quantity=sci.getQuantity();
            int storeNumber=bookService.getBook(sci.getBook().getId()).getStoreNumber();

            if(quantity>storeNumber){
                errors.append(sci.getBook().getTitle()+" inventory is not enough<br>");
            }

        }
        return errors;
    }

    public StringBuffer validateUser(String username,String accountId){
        boolean flag=false;
        User user = userService.getUserByUserName(username);
        if (user!=null){
            int accountId2= user.getAccountId();
            if(accountId.trim().equals(""+accountId2)){
                flag=true;
            }
        }

        StringBuffer errors2= new StringBuffer("");
        if(!flag){
            errors2.append("username and account id do not match");
        }

        return errors2;

    }


    public StringBuffer validateFormField(String username, String accountId){
        StringBuffer errors= new StringBuffer("");
        if(username==null || username.trim().equals("")){
            errors.append("username could not be null");
            //System.out.println(errors);

        }

        if(accountId==null || accountId.trim().equals("")){
            errors.append("account id could not be null");

        }

        return errors;
    }

    protected void updateItemQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr=request.getParameter("id");
        String quantityStr=request.getParameter("quantity");

        ShoppingCart sc=BookStoreWebUtils.getShoppingCart(request);
        int id=-1;
        int quantity=-1;
        try{
            id=Integer.parseInt(idStr);
            quantity=Integer.parseInt(quantityStr);
        }catch(Exception e){}

        if(id>0 && quantity>0)
            bookService.updateItemQuantity(sc,id,quantity);

        Map<String,Object> result= new HashMap<String,Object>();
        result.put("bookNumber",sc.getBookNumber());
        result.put("totalMoney",sc.getTotalMoney());

        Gson gson= new Gson();
        String jsonStr=gson.toJson(result);
        response.setContentType("text/javascript");
        response.getWriter().print(jsonStr);
    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCart sc= BookStoreWebUtils.getShoppingCart(request);
        bookService.clearShoppingCart(sc);
        request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request,response);

    }

    protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr=request.getParameter("id");
        int id=-1;
        try{
            id=Integer.parseInt(idStr);
        }catch(Exception e){}
        ShoppingCart sc=BookStoreWebUtils.getShoppingCart(request);
        bookService.removeItemFromShoppingCart(sc,id);

        if(sc.isEmpty()){
            request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request,response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);

    }




    protected void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=request.getParameter("page");
        request.getRequestDispatcher("/WEB-INF/pages/"+page+".jsp").forward(request,response);
    }

    protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String  idStr= request.getParameter("id");
        int id=-1;
        boolean flag=false;
        try{
            id=Integer.parseInt(idStr);
        }catch(Exception e){}

        if(id>0){
            ShoppingCart sc= BookStoreWebUtils.getShoppingCart(request);
            flag= bookService.addToCart(id,sc);}

        if (flag){
            getBooks(request,response);
            return;
        }

        response.sendRedirect(request.getContextPath()+"/error-1.jsp");
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String idStr=request.getParameter("id");
        int id=-1;

        Book book =null;
        try{
            id=Integer.parseInt(idStr);
        }catch(NumberFormatException e){}

        if(id>0)
            book=bookService.getBook(id);

        if(book==null){
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");
            return;
        }
        request.setAttribute("book",book);
        request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request,response);
    }


    protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String pageNotStr=request.getParameter("pageNo");
        String minPriceStr=request.getParameter("minPrice");
        String maxPriceStr=request.getParameter("maxPrice");

        int pageNo=1;
        int minPrice=0;
        int maxPrice=Integer.MAX_VALUE;

        try {
            pageNo=Integer.parseInt(pageNotStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            minPrice=Integer.parseInt(minPriceStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            maxPrice=Integer.parseInt(maxPriceStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        CriteriaBook criteriaBook = new CriteriaBook(minPrice,maxPrice,pageNo);
        Page<Book> page= bookService.getPage(criteriaBook);
        request.setAttribute("bookpage",page);
        request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request,response);

    }
}
