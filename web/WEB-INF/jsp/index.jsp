<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/12
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>主页</title>
  </head>
  <body>
  当前用户：${USER_SESSION.username}
  <a href="${pageContext.request.contextPath}/sign_out">退出</a>
  </body>
</html>
