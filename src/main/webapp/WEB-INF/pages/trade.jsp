<%--
  Created by IntelliJ IDEA.
  User: yanxuanwu
  Date: 4/24/22
  Time: 2:38 PM
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
        <h4>User:${user.username}</h4>
        <br><br>
        <table>

            <c:forEach items="${user.trades}" var="trade">
                <tr>
                    <td>

                    <table border="1" cellpadding="10">

                        <tr>
                            <td colspan="3">TradeTime:${trade.tradeTime}</td>
                        </tr>


                        <br><br>
                        <c:forEach items="${trade.items}" var="item">
                            <tr>
                                <td>${item.book.title}</td>
                                <td>${item.book.price}</td>
                                <td>${item.quantity}</td>
                            </tr>


                        </c:forEach>
                    </table>
                        <br><br>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </center>


</body>
</html>
