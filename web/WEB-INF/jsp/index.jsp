<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/12
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="simplebbs" uri="http://zenghx.tk/common/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>主页</title>
  </head>
  <body>
  当前用户：${USER_SESSION.user_name}
  <a href="${pageContext.request.contextPath}/sign_out">退出</a>

  </body>
</html>
