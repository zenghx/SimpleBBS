<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/18
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}——SimpleBBS</title>
    <link rel="stylesheet" type="text/css" href="https://zcdn.yce.ink/wangEditor.min.css"/>
</head>
<body>
<jsp:include page="../component/post_content.jsp"></jsp:include>
<div id="comments"></div>
<div id="page"></div>
<jsp:include page="../component/editor.jsp"></jsp:include>
<button type="button" onclick="return "/>
<script>
    let page=1;
    let page_size=20;
    $(function load_comments() {
        let param=window.location.href.split('/').pop();
        let reg = new RegExp("^[0-9]*$");
        let type;
        if(reg.test(param))
            type="post";
        else type="user";
        $.ajax({
            url:"${pageContext.request.contextPath}/comment",
            type:"post",
            data:JSON.stringify({target_type:type,target:param,page:page,page_size:page_size}),
            contentType:"application/json;charset=UTF-8",
            dataType:"json",
            success:function (data) {
                console.log(data);
            },
            error:function () {
                console.log("error");
            }

        })
    })

</script>
</body>
</html>
