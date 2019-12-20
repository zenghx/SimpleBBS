<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html charset="UTF-8">
<head>
    <title>发表新帖子</title>
    <link rel="stylesheet" type="text/css" href="https://zcdn.yce.ink/wangEditor.min.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<input type="text" placeholder="请输入帖子标题" id="post_title" maxlength="70" onchange="maxlength()"/><span id="ttmessage"></span>
<jsp:include page="../component/editor.jsp"></jsp:include>
<select id="select_section" name="select_section">
<option value="">请选择板块...</option>
<c:forEach items="${allSections}" var="item">
    <option value="${item.section_id}"
        <c:if test="${not item.allow_new_post}">disabled</c:if>>
            ${item.section_name}
    </option>
</c:forEach>
</select>
<button type="button" onclick="return submit_new_post()" >发表</button>
<script>
    function maxlength() {
        $('#ttmessage').text("");
        if($("#post_title").val().length>70) {
            $("#post_title").val($("#post_title").val().slice(0,70));
        }

    }
    function submit_new_post() {
        let tt=$('#post_title').val();
        let section=$('#select_section').val();
        if(tt.trim().length==0){
            $('#ttmessage').text("标题不能为空");
            return;
        }
        if(section==""){
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
                if(data.status=="succeed"){
                    alert("发帖成功");
                    window.location.href="${pageContext.request.contextPath}/post/"+data.post_id;
                }
                else {
                    console.log(data.status);
                    alert("发帖失败，请稍后重试");
                }
            },
            error:function () {
                console.log("error");
            }
        })
    }
</script>

</body>
</html>

