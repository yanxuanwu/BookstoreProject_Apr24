<%--
  Created by IntelliJ IDEA.
  User: yanxuanwu
  Date: 4/20/22
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/commons/common.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">

        $(function(){

            $('#pageNo').change(function(){
                var val=$(this).val();
                val=$.trim(val);
                var flag=false;
                var reg=/^\d+$/g;
                var pageNo=0;
                if(reg.test(val)){
                    pageNo=parseInt(val);
                    if(pageNo>=1&& pageNo<=parseInt("${bookpage.totalPageNumber}")){
                        flag=true;
                    }
                }

                if(!flag){
                    alert("Illegal Input");
                    $(this).val("");
                    return;
                }

                var href="bookServlet?method=getBooks&pageNo="+pageNo+"&"+$(":hidden").serialize();
                window.location.href=href;
            })
        })
    </script>
    <%@include file="/WEB-INF/commons/queryCondition.jsp"%>
</head>

<body>
    <center>
        <c:if test="${param.title!=null}">
            you have successfully put ${param.title} into the shopping cart
            <br><br>
        </c:if>
        <c:if test="${!empty sessionScope.ShoppingCart.books}">
            Your shopping cart has ${sessionScope.ShoppingCart.bookNumber} books,<a href="bookServlet?method=forwardPage&page=cart&pageNo=${bookpage.pageNo}">check your shopping cart</a>
        </c:if>

        <br><br>
        <form action="bookServlet?method=getBooks" method="post">
            Price:
            <input type="text" size="1" name="minPrice"/> -
            <input type="text" size="1" name="maxPrice"/>

            <input type="submit" value="submit"/>
        </form>

        <br><br>
        <table cellpadding="10">
            <c:forEach items="${bookpage.list}" var="book">
                <tr>
                    <td>
                        <a href="bookServlet?method=getBook&pageNo=${bookpage.pageNo}&id=${book.id}">${book.title}</a>
                        <br>
                        ${book.author}

                    </td>
                    <td>${book.price}</td>
                    <td><a href="bookServlet?method=addToCart&pageNo=${bookpage.pageNo}&id=${book.id}&title=${book.title}">add to the shopping cart</a></td>
                </tr>
            </c:forEach>

        </table>

        <br><br>
        total pages numbers: ${bookpage.totalPageNumber}
        &nbsp;&nbsp;
        Page ${bookpage.pageNo}
        &nbsp;&nbsp;

        <c:if test="${bookpage.hasPrev}">
            <a href="bookServlet?method=getBooks&pageNo=1"> first page</a>
            &nbsp;&nbsp;
            <a href="bookServlet?method=getBooks&pageNo=${bookpage.prevPage}">previous</a>

        </c:if>

        &nbsp;&nbsp;
        <c:if test="${bookpage.hasNext}">
            <a href="bookServlet?method=getBooks&pageNo=${bookpage.nextPage}"> next</a>
            &nbsp;&nbsp;
            <a href="bookServlet?method=getBooks&pageNo=${bookpage.totalPageNumber}">last page</a>
        </c:if>

    </center>

</body>
</html>
