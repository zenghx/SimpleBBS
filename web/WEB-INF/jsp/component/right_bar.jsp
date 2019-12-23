<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/22
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="box">
    <c:if test="${sessionScope.USER_SESSION==null}">
        <div class="inner">
            <div class="sep5"></div>
            <div align="center"><a href="${pageContext.request.contextPath}/sign_up"
                                   class="super normal button">现在注册</a>
                <div class="sep5"></div>
                <div class="sep10"></div>
                已注册用户请 &nbsp;<a href="${pageContext.request.contextPath}/sign_in">登录</a></div>
        </div>
    </c:if>
    <c:if test="${sessionScope.USER_SESSION!=null}">
        <div class="cell">
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <tbody>
                <tr>
                    <td width="48" valign="top"><a
                            href="${pageContext.request.contextPath}/member/${sessionScope.USER_SESSION.user_name}"><img
                            src="${sessionScope.USER_SESSION.avatar_url}??imageView2/1/w/48/h/48" class="avatar"
                            border="0" align="default" style="max-width: 48px; max-height: 48px;"></a></td>
                    <td width="10" valign="top"></td>
                    <td width="auto" align="left"><span class="bigger"><a
                            href="${pageContext.request.contextPath}/member/${sessionScope.USER_SESSION.user_name}">${sessionScope.USER_SESSION.user_name}</a></span>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="sep10"></div>
        </div>
        <div class="cell" style="padding: 5px;">
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <tbody>
                <tr>
                    <td width="28"><a href="${pageContext.request.contextPath}/new_post"><img
                            src="https://www.v2ex.com/static/img/essentials/compose.png?v=b9e1f045f4ad639733bf9f6dbc62ed4c"
                            width="28" border="0"></a></td>
                    <td width="10"></td>
                    <td width="auto" valign="middle" align="left"><a href="${pageContext.request.contextPath}/new_post">创作新主题</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </c:if>
</div>
