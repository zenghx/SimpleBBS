<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/14
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>注册</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/enc-base64.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/hmac-sha256.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/core.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
    <script>
        function hashpwd(){
            let usrname=document.getElementById("username");
            let pwd=document.getElementById("password");
            let message=pwd.value+usrname.value;
            let hash=CryptoJS.HmacSHA256(message,"digest");
            pwd.value=CryptoJS.enc.Base64.stringify(hash);
            return true;
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/sign_up" method="post" onsubmit="hashpwd()">
    用户名：<input type="text" id="username" name="username"/><br/>
    密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="password"/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
