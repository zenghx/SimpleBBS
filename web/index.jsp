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
<div class="container">
  <!-- 入门-->
  <!-- 登录界面 -->
  <div class="page-header">
    <h1>用户登录</h1>&nbsp; <font color="red">${error_message}</font>
  </div>
  <form class="form-horizontal" method="post"
        action="${pageContext.request.contextPath}/user/userLogin.do">
    <div class="form-group">
      <div class="col-sm-4">
        <input class="form-control" value="" placeholder="用户名/邮箱"
               type="text" id="loginName" name="loginName" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-4">
        <input class="form-control" placeholder="密码" id="passWord"
               type="password" name="password" />
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-4">
        <span style="color: red;"></span>
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-4">
        <div class="btn-group btn-group-justified" role="group"
             aria-label="...">
          <div class="btn-group" role="group">
            <button type="submit" id="loginBtn" class="btn btn-success">
              <span class="glyphicon glyphicon-log-in"></span>&nbsp;登录
            </button>
          </div>
          <div class="btn-group" role="group">
            <button type="button" class="btn btn-danger">
              <span class="glyphicon glyphicon-edit"></span>注册
            </button>
          </div>
        </div>
      </div>
    </div>
  </form>
  <hr>
</div>
</body>
</html>
