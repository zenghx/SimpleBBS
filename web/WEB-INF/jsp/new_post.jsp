<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>发表新帖子</title>
    <link rel="stylesheet" type="text/css" href="https://zcdn.yce.ink/wangEditor.min.css"/>
</head>
<body>
<input type="text" placeholder="请输入帖子标题" id="post_title"/>
<jsp:include page="editor.jsp"></jsp:include>
<select>

</select>
<button type="button" onclick="return submit()" >提交</button>
<script>
    function submit() {
        let author=6;
        let tt=$('#post_title').val();
        let section=1;
        $.ajax({
            url:"${pageContext.request.contextPath}/new_post",
            type:"post",
            data:JSON.stringify({author:author,title:tt,content:editor.txt.html(),section_id:section}),
            contentType:"application/json;charset=UTF-8",
            dataType:"json",
            success:function (data) {
                if(data.status=="succeed"){
                    alert("发帖成功");
                    window.location.href="${pageContext.request.contextPath}/post/"+data.post_id;
                }
                else alert("发帖失败，请稍后重试");
            },
            error:function () {
                console.log("error");
            }
        })
    }
</script>

</body>
</html>

