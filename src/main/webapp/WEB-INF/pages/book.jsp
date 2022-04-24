<%--
  Created by IntelliJ IDEA.
  User: yanxuanwu
  Date: 4/22/22
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/commons/common.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>


</head>
<body>
    

    <center>
        <br><br>
        Title: ${book.title}
        <br><br>
        Author:${book.author}
        <br><br>
        Price:${book.price}
        <br><br>
        PublishingDate:${book.publishingDate}
        <br><br>
        Remark:${book.remark}
        <br><br>

        <a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">continue shopping</a>

    </center>

</body>
</html>
