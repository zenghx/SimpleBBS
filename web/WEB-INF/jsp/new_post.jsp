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
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="https://zcdn.yce.ink/wangEditor.min.css"/>
</head>
<body>
<input type="text" placeholder="请输入帖子标题" id="post_title"/>
<div id="div1">
    <p>请输入内容...</p>
</div>
<select>

</select>
<button type="button" onclick="return submit()" >提交</button>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://zcdn.yce.ink/wangEditor.min.js"></script>
<script>
    const E = window.wangEditor;
    const editor = new E('#div1');
    editor.create()
</script>
<script>
    function submit() {
        let author=6;
        let tt=$('#post_title').val();
        let section=1;
        $.ajax({
            url:"${pageContext.request.contextPath}/new_post",
            type:"post",
            async:false,
            data:JSON.stringify({author:author,title:tt,content:editor.txt.html(),section_id:section}),
            contentType:"application/json;charset=UTF-8",
            dataType:"json",
            success:function (data) {
                console.log(data.status);
            }
        })
    }
</script>

</body>
</html>

