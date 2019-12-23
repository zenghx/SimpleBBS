<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/18
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${title}——SimpleBBS</title>
    <link rel="icon" href="https://s.zenghx.tk/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="https://zcdn.yce.ink/wangEditor.min.css"/>
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
            <jsp:include page="../component/post_content.jsp"></jsp:include>
            <div class="sep20"></div>
            <jsp:include page="../component/comment_content.jsp"></jsp:include>
            <div class="sep20"></div>
            <c:if test="${sessionScope.USER_SESSION!=null}">
                <div class="box">
                    <div class="cell">
                        添加一条新回复
                    </div>
                    <div class="cell">
                        <form>
                            <jsp:include page="../component/editor.jsp"></jsp:include>
                            <div class="sep10"></div>
                            <div class="fr">
                                <div class="sep5"></div>
                                <span class="gray">请尽量让自己的回复能够对别人有帮助</span></div>
                            <input type="button" onclick="submit_comment()" value="回复" class="super normal button">
                            <div id="nc_info" style="color:red;"></div>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <div class="c"></div>
    <div class="sep20"></div>
</div>
<jsp:include page="../component/footer.jsp"></jsp:include>
<script>
    load_page();
    activate_page_button($("#next"));
    load_comments();

    function submit_comment() {
        let post_id = window.location.href.split('/').pop();
        post_id = post_id.split("?");
        post_id = post_id[0];
        let content = editor.txt.text();
        if (content.trim().length > 0) {
            $.ajax({
                url: "${pageContext.request.contextPath}/new_comment",
                type: "post",
                data: JSON.stringify({post_id: post_id, content: editor.txt.html()}),
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (data) {
                    if (data.status == 200) {
                        alert("回复成功");
                        window.location.href = location;
                    } else {
                        console.log(data);
                        $("#nc_info").text("回复失败，请稍后重试");
                    }
                },
                error: function () {
                    $("#nc_info").text("与服务器通信时出现错误");
                }
            })
        } else $("#nc_info").text("请不要回复空白字符");
    }
</script>
</body>
</html>
