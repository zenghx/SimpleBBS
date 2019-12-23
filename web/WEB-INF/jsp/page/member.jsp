<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/23
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>SimpleBBS › ${user.user_name}</title>
    <link rel="icon" href="https://s.zenghx.tk/favicon.ico" type="image/x-icon"/>
    <link href="https://zcdn.yce.ink/css/stylesheet.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
                <div class="cell">
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tbody>
                        <tr>
                            <td width="73" valign="top" align="center"><img
                                    src="${user.avatar_url}?imageView2/1/w/73/h/73" class="avatar" border="0"
                                    align="default">
                                <div class="sep10"></div>
                            </td>
                            <td width="10"></td>
                            <td width="auto" valign="top" align="left">
                                <h1 style="margin-bottom: 5px;">${user.user_name}</h1>
                                <div class="sep10"></div>
                                <span class="gray">
                                    SimpleBBS 第 ${user.user_id} 号会员,
                                    <c:if test="${user.gender==0}">性别保密。</c:if>
                                    <c:if test="${user.gender==1}">性别：男。</c:if>
                                    <c:if test="${user.gender==2}">性别：女</c:if>
                                    <div class="sep5"></div>
                                    生日：
                                    <c:if test="${birthday==null}">
                                        保密。
                                    </c:if>
                                    <c:if test="${birthday!=null}">
                                        ${birthday}
                                    </c:if>
                                </span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="sep5"></div>
                </div>
                <div class="sep20"></div>
                <div class="box">
                    <div class="cell_tabs">
                        <div class="fl"><img src="${user.avatar_url}?imageView2/1/w/48/h/48" width="24"
                                             style="border-radius: 24px; margin-top: -2px;" border="0"></div>
                        <a href="/member/${user.user_name}" class="cell_tab_current">${user.user_name} 创建的所有主题</a></div>
                    <div id="post_content">

                    </div>
                    <jsp:include page="../component/page_bar.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
    <div class="c"></div>
    <div class="sep20"></div>
</div>
<jsp:include page="../component/footer.jsp"></jsp:include>
<script>
    let page = 1;
    let page_size = 5;
    let total_pages;
    let target = "post";
    load_post_page();
    load_posts();
    activate_page_button($("#next"));
</script>
</body>
</html>
