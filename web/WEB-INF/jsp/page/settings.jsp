<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/24
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>SimpleBBS › 设置</title>
    <link rel="icon" href="https://s.zenghx.tk/favicon.ico" type="image/x-icon"/>
    <link href="https://zcdn.yce.ink/css/stylesheet.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/enc-base64.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/hmac-sha256.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/core.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
</head>
<body>
<jsp:include page="../component/nav.jsp"></jsp:include>
<div id="Wrapper">
    <div class="content">
        <div id="Rightbar">
            <div class="sep20"></div>
            <jsp:include page="../component/right_bar.jsp"></jsp:include>
        </div>
        <div id="Main">
            <div class="sep20"></div>
            <div class="box">
                <div class="header"><a href="${pageContext.request.contextPath}/">SimpleBBS</a> <span class="chevron">&nbsp;›&nbsp;</span>
                    设置&nbsp;&nbsp;<span class="fade">${msg}</span></div>
                <div class="inner">
                    <form method="post" action="${pageContext.request.contextPath}/settings">
                        <table cellpadding="5" cellspacing="0" border="0" width="100%">
                            <tbody>
                            <tr>
                                <td width="120" align="right"><img src="${user.avatar_url}?imageView2/1/w/48/h/48"
                                                                   class="avatar" border="0" align="default"
                                                                   style="max-width: 24px; max-height: 24px;"></td>
                                <td width="auto" align="left">SimpleBBS 第 ${user.user_id} 号会员</td>
                            </tr>
                            <tr>
                                <td width="120" align="right">用户名</td>
                                <td width="auto" align="left">${user.user_name} </td>
                            </tr>
                            <tr>
                                <td width="120" align="right">出生日期</td>
                                <td width="auto" align="left"><input type="date" id="bday" name="birthday"
                                                                     value="${birthday}" class="sl"></td>
                            </tr>
                            <tr>
                                <td width="120" align="right">性别</td>
                                <td width="auto" align="left">
                                    <select id="gender" name="gender" value="${user.gender}" class="sl">
                                        <option value="0" <c:if test="${user.gender==0}">selected</c:if>>保密</option>
                                        <option value="1" <c:if test="${user.gender==1}">selected</c:if>>男</option>
                                        <option value="2" <c:if test="${user.gender==2}">selected</c:if>>女</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td width="120" align="right"></td>
                                <td width="auto" align="left"><input type="submit" class="super normal button"
                                                                     value="保存设置"></td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <div class="sep20"></div>
            <div class="box">
                <div class="cell">
                    <div class="fr"><span class="fade">如果你不打算更改密码，请留空以下区域</span></div>
                    更改密码
                </div>
                <div class="cell" id="message"></div>
                <div class="inner">
                    <form>
                        <table cellpadding="5" cellspacing="0" border="0" width="100%">
                            <tbody>
                            <tr>
                                <td width="120" align="right">当前密码</td>
                                <td width="auto" align="left"><input type="password" class="sl" id="password_current"
                                                                     value=""></td>
                            </tr>
                            <tr>
                                <td width="120" align="right">新密码</td>
                                <td width="auto" align="left"><input type="password" class="sl" id="password_new"
                                                                     value=""></td>
                            </tr>
                            <tr>
                                <td width="120" align="right">再次输入新密码</td>
                                <td width="auto" align="left"><input type="password" class="sl" id="password_again"
                                                                     value=""></td>
                            </tr>
                            <tr>
                                <td width="120" align="right"></td>
                                <td width="auto" align="left"><input type="button" class="super normal button"
                                                                     onclick="check_pwd()" value="更改密码"></td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="c"></div>
    <div class="sep20"></div>
</div>
<jsp:include page="../component/footer.jsp"></jsp:include>
<script>
    function check_pwd() {
        let usr_name = "${user.user_name}";
        let pwd = $("#password_current").val();
        let pwd_new = $("#password_new").val();
        let pwd_again = $("#password_again").val();
        if (pwd.length > 20 || pwd.length < 6) {
            $("#message").text("密码请输入6-20位字符");
            return;
        }
        if (pwd_again.length > 20 || pwd_again.length < 6) {
            $("#message").text("密码请输入6-20位字符");
            return;
        }
        if (pwd_new.length > 20 || pwd_new.length < 6) {
            $("#message").text("密码请输入6-20位字符");
            return;
        }
        if (pwd_new != pwd_again) {
            $("#message").text("两次密码输入不一致，请检查");
            return;
        }
        if (pwd_new == pwd) {
            $("#message").text("新旧密码不能相同");
            return;
        }
        let old_hash = CryptoJS.HmacSHA256(pwd + usr_name, "digest");
        let new_hash = CryptoJS.HmacSHA256(pwd_new + usr_name, "digest");
        $.ajax({
            url: "${pageContext.request.contextPath}/settings/password",
            type: "post",
            data: JSON.stringify({
                password_current: CryptoJS.enc.Base64.stringify(old_hash),
                password_new: CryptoJS.enc.Base64.stringify(new_hash)
            }),
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data.status == 200) {
                    $("#message").text("更新密码成功,3秒后跳转至登录页面");
                    setTimeout(function () {
                        window.location.href = "${pageContext.request.contextPath}/sign_in"
                    }, 3000)
                } else $("#message").text(data.msg);

            },
            error: function (data) {
                console.log("与服务请通信出错" + data);
            }
        })

    }
</script>
</body>
</html>
