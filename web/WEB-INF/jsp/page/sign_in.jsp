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
    <title>登录</title>
    <link rel="icon" href="https://s.zenghx.tk/favicon.ico" type="image/x-icon"/>
    <link href="https://zcdn.yce.ink/css/stylesheet.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/enc-base64.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/hmac-sha256.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/core.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        function hashpwd() {
            let usrname = document.getElementById("uname");
            let pwd = document.getElementById("upass");
            let oError = document.getElementById("error_box");
            if (pwd.value == null || usrname.value == null) {
                oError.innerText = "用户名或密码不能为空";
                return false;
            }
            if (usrname.value.length > 20 || usrname.value.length < 5) {
                oError.innerHTML = "用户名请输入5-20位字符";
                return false;
            } else if ((usrname.value.charCodeAt(0) >= 48) && (usrname.value.charCodeAt(0) <= 57)) {
                oError.innerHTML = "用户名首字符必须为字母";
                return false;
            }else for(var i=0;i<usrname.value.charCodeAt(i);i++){
                if ((usrname.value.charCodeAt(i) < 48) || (usrname.value.charCodeAt(i) > 57) && (usrname.value.charCodeAt(i) < 97) || (usrname.value.charCodeAt(i) > 122)) {
                    oError.innerHTML = "用户名必须为字母跟数字组成";
                    return false;
                }
            }

            if (pwd.value.length > 20 || pwd.value.length < 6) {
                oError.innerHTML = "密码请输入6-20位字符";
                return false;
            }
            let message = pwd.value + usrname.value;
            let hash = CryptoJS.HmacSHA256(message, "digest");
            $("#hashed_pwd").val(CryptoJS.enc.Base64.stringify(hash));
            return true;
        }
    </script>
</head>
<body>
<jsp:include page="../component/nav.jsp"></jsp:include>
<div id="Wrapper">
    <div class="content">
        <div id="Main">
            <div class="sep20"></div>
            <div class="box">
                <div class="header">登录</div>
                <div class="cell">
                    <form action="${pageContext.request.contextPath}/sign_in" method="post" onsubmit="return hashpwd()">
                        <div class="input_box">
                            <input id="uname" class="sl" type="text" name="username" placeholder="请输入用户名">
                        </div>
                        <br>
                        <div class="input_box">
                            <input id="upass" class="sl" type="password" name="pwd" placeholder="请输入密码">
                            <input name="password" id="hashed_pwd" hidden>
                        </div>

                        <div id="error_box" style="color: red">${msg}<br></div>
                        <div class="input_box1">
                            <button type="submit" class="super normal button">登录</button>
                            <a href="${pageContext.request.contextPath}/sign_up">
                                <input type="button" class="super normal button" name="regist" value="无用户，先注册">
                            </a>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
    <div class="c"></div>
    <div class="sep20"></div>
</div>
<jsp:include page="../component/footer.jsp"></jsp:include>

</body>
</html>
