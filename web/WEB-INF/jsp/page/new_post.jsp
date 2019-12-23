<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>发表新帖子</title>
    <link rel="icon" href="https://s.zenghx.tk/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="https://zcdn.yce.ink/wangEditor.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://zcdn.yce.ink/css/stylesheet.css" rel="stylesheet">
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
                    <a href="${pageContext.request.contextPath}/">SimpleBBS</a>
                    <span class="chevron">&nbsp;›&nbsp;</span> 创作新主题
                </div>
                <div id="compose">
                    <div class="cell">
                        帖子标题
                        <div class="fr fade" id="ttmessage"></div>
                    </div>
                    <div class="cell" style="padding: 0">
                        <input type="text" class="msl" placeholder="请输入帖子标题" id="post_title" maxlength="70"
                               onchange="maxlength()"/>
                    </div>
                    <div class="cell">
                        正文
                    </div>
                    <div style="text-align: left; border-bottom: 1px solid #e2e2e2; font-size: 14px; line-height: 120%;">
                        <jsp:include page="../component/editor.jsp"></jsp:include>
                    </div>
                    <div class="cell">
                        <select id="select_section" name="select_section">
                            <option value="">请选择板块...</option>
                            <c:forEach items="${allSections}" var="item">
                                <option value="${item.section_id}"
                                        <c:if test="${not item.allow_new_post}">disabled</c:if>>
                                        ${item.section_name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="cell">
                        <button class="super normal button" type="button" onclick="return submit_new_post()">发布主题
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="c"></div>
    <div class="sep20"></div>
</div>

<jsp:include page="../component/footer.jsp"></jsp:include>
<script>
    function maxlength() {
        $('#ttmessage').text("");
        if ($("#post_title").val().length > 70) {
            $("#post_title").val($("#post_title").val().slice(0, 70));
        }

    }

    function submit_new_post() {
        let tt = $('#post_title').val();
        let section = $('#select_section').val();
        if (tt.trim().length == 0) {
            $('#ttmessage').text("标题不能为空");
            return;
        }
        if (section == "") {
            $('#ttmessage').text("请选择板块");
            return;
        }
        console.log(section);
        $.ajax({
            url:"${pageContext.request.contextPath}/new_post",
            type:"post",
            data:JSON.stringify({title:tt,content:editor.txt.html(),section_id:section}),
            contentType:"application/json;charset=UTF-8",
            dataType:"json",
            success:function (data) {
                if(data.status==200){
                    alert("发帖成功");
                    window.location.href="${pageContext.request.contextPath}/post/"+data.post_id;
                }
                else {
                    console.log(data);
                    alert("发帖失败，请稍后重试");
                }
            },
            error:function () {
                console.log("与服务器通信时出现错误");
            }
        })
    }
</script>
</body>
</html>

