<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/20
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>SimpleBBS › ${SectionName}</title>
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
                <div class="node_header">
                    <div class="node_avatar"></div>
                    <div class="node_info">
                        <a href="${pageContext.request.contextPath}/">SimpleBBS</a>
                        <span class="chevron">&nbsp;›&nbsp;</span>
                        ${SectionName}
                        <div class="sep10"></div>
                        <div class="sep5"></div>
                        <div class="sep10"></div>
                    </div>
                </div>
                <div id="TopicsNode">
                    <div id="post_content"></div>
                    <jsp:include page="../component/page_bar.jsp"></jsp:include>
                </div>
            </div>
            <div class="sep20"></div>
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
    load_post_page();
    load_posts();
    activate_page_button($("#next"));
</script>
</body>
</html>
