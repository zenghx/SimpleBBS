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
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../component/nav.jsp"></jsp:include>
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