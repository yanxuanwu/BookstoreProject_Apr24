<%--
  Created by IntelliJ IDEA.
  User: yanxuanwu
  Date: 4/23/22
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/commons/common.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <br><br>
        Total book number: ${sessionScope.ShoppingCart.bookNumber}
        <br>
        You should pay: $ ${sessionScope.ShoppingCart.totalMoney}
        <br><br>

        <c:if test="${requestScope.errors!=null}">
            <font color="red">${requestScope.errors}

            </font>
        </c:if>

        <form action="bookServlet?method=cash" method="post">
            <table cellpadding="10">
                <tr>
                    <td>credit card holder:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>credit card account:</td>
                    <td><input type="text" name="accountId"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="Submit"></td>
                </tr>
            </table>
        </form>
    </center>

</body>
</html>
