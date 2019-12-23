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
    <link rel="icon" href="https://s.zenghx.tk/favicon.ico" type="image/x-icon"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/enc-base64.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/hmac-sha256.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/core.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://zcdn.yce.ink/css/stylesheet.css" rel="stylesheet">
    <script>
        function sub() {
            let usrname = document.getElementById("uname");
            let pwd = document.getElementById("upass");
            let oError = document.getElementById("error_box");
            if (pwd.value == "" || usrname.value == "") {
                oError.innerText = "用户名或密码不能为空";
                return;
            }
            if (usrname.value.length > 20 || usrname.value.length < 5) {
                oError.innerHTML = "用户名请输入5-20位字符";
                return;
            } else if ((usrname.value.charCodeAt(0) >= 48) && (usrname.value.charCodeAt(0) <= 57)) {
                oError.innerHTML = "首字符必须为字母";
                return;
            } else for (var i = 0; i < usrname.value.charCodeAt(i); i++) {
                if ((usrname.value.charCodeAt(i) < 48) || (usrname.value.charCodeAt(i) > 57) && (usrname.value.charCodeAt(i) < 97) || (usrname.value.charCodeAt(i) > 122)) {
                    oError.innerHTML = "用户名必须为字母跟数字组成";
                    return;
                }
            }
            if (pwd.value.length > 20 || pwd.value.length < 6) {
                oError.innerHTML = "密码请输入6-20位字符";
                return;
            }
            let message = pwd.value + usrname.value;
            let hash = CryptoJS.HmacSHA256(message, "digest");
            $.ajax({
                url: "${pageContext.request.contextPath}/sign_up",
                type:"post",
                data:JSON.stringify({user_name:usrname.value,pwd_hash:CryptoJS.enc.Base64.stringify(hash)}),
                contentType:"application/json;charset=UTF-8",
                dataType:"json",
                success:function (data) {
                    if(data.status===400)
                        oError.innerText="用户名已被占用！";
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
<jsp:include page="../component/nav.jsp"></jsp:include>
<div id="Wrapper">
    <div class="content">
        <div id="Main">
            <div class="sep20"></div>
            <div class="box">
                <div class="header">注册</div>
                <div class="cell" margin="auto">
                    <p>账号:
                        <input id="uname" class="sl" type="text" name="username" placeholder="账号首字符为字母">
                    </p>
                    <p>密码:
                        <input id="upass" class="sl" type="password" name="password" placeholder="请输入密码">
                    </p>
                    <div id="error_box" style="color: red"><br></div>
                    <br>
                    <button onclick="sub()" class="super normal button">注册</button>
                </div>
            </div>
        </div>
    </div>
    <div class="c"></div>
    <div class="sep20"></div>
</div>
</div>
<jsp:include page="../component/footer.jsp"></jsp:include>

</body>
</html>
