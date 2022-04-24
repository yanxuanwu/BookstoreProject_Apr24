<%--
  Created by IntelliJ IDEA.
  User: yanxuanwu
  Date: 4/24/22
  Time: 2:55 PM
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
      <form action="usersServlet" method="post">
          username:<input type="text" name="username"/>
          <input type="submit" value="Submit"/>
      </form>
  </center>

</body>
</html>
