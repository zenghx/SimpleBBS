<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/17
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="https://zcdn.yce.ink/wangEditor.min.css"/>
</head>
<body>
<div id="div1">
    <p>请输入内容...</p>
</div>
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
        console.log(editor.txt.html());
    }
</script>

</body>
</html>
