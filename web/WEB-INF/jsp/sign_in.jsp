<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/14
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>登陆页面</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/enc-base64.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/hmac-sha256.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/core.min.js" integrity="sha256-u6BamZiW5tCemje2nrteKC2KoLIKX9lKPSpvCkOhamw=" crossorigin="anonymous"></script>
    <script>
        function hashpwd(){
            let usrname=document.getElementById("username");
            let pwd=document.getElementById("password");
            let message=pwd+usrname;
            let hash=CryptoJS.HmacSHA256(message,"digest");
            return CryptoJS.enc.Base64.stringify(hash);
        }
    </script>
</head>
<body>
{$msg}
<form action="${pageContext.request.contextPath}/sign_in" method="post" onsubmit="hashpwd()">
    用户名：<input type="text" id="username" name="username"/><br/>
    密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="password"/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
