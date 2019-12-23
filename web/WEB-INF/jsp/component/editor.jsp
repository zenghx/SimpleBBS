<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/18
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>


<div id="div1"><p>请输入内容...</p></div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://zcdn.yce.ink/wangEditor.min.js"></script>
<script>
    const E = window.wangEditor;
    const editor = new E('#div1');
    editor.create()
</script>
