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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        function sub(){
            let usrname=document.getElementById("username");
            let pwd=document.getElementById("password");
            let message=pwd.value+usrname.value;
            let hash=CryptoJS.HmacSHA256(message,"digest");
            $.ajax({
                url:"${pageContext.request.contextPath}/sign_up",
                type:"post",
                async:false,
                data:JSON.stringify({user_name:usrname.value,pwd_hash:CryptoJS.enc.Base64.stringify(hash)}),
                contentType:"application/json;charset=UTF-8",
                dataType:"json",
                success:function (data) {
                    if(data==false)
                        $("#usrnamemsg").text("用户名已被占用！")
                    else {
                        alert("注册成功");
                        window.location.href="${pageContext.request.contextPath}/sign_in"
                    }
                }
            })


        }

    </script>
</head>
<body>
<form>
    用户名：<input type="text" id="username" name="username"/>
    <span style="color: red; "> <span id="usrnamemsg"></span></span> <br/>
    密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="password"/>
    <input type="button" value="注册" onclick="sub()"/>
</form>
</body>
</html>
