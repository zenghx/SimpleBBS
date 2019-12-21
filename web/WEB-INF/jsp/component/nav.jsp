<%@ page import="com.simplebbs.po.UserInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/20
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="Top">
    <div class="content">
        <div style="padding-top: 6px;">
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <tbody>
                <tr>
                    <td width="110" align="left">
                        <a href="${pageContext.request.contextPath}/" name="index" title="SimpleBBS">
                            <div id="Logo">
                                SimpleBBS
                            </div>
                        </a>
                    </td>
                    <c:if test="${sessionScope.USER_SESSION==null}">
                        <td width="570" align="right" style="padding-top: 2px;">
                        <a href="${pageContext.request.contextPath}/sign_up" class="top">注册</a>&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/sign_in" class="top">登录</a>
                    </td>
                    </c:if>
                    <c:if test="${sessionScope.USER_SESSION!=null}">
                        <td width="570" align="right" style="padding-top: 2px;">
                            <a href="${pageContext.request.contextPath}/member/${sessionScope.USER_SESSION.user_name}">个人中心</a>&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/settings">设置</a>&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/sign_out">退出</a>
                        </td>
                    </c:if>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
