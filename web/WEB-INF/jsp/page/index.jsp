<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/12
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>主页</title>
  <link href="https://zcdn.yce.ink/css/stylesheet.css" rel="stylesheet">
  <link rel="icon" href="https://s.zenghx.tk/favicon.ico" type="image/x-icon"/>
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
        <div id="post_content">
          <img src="https://zcdn.yce.ink/img/loading.gif"/>
        </div>
        <jsp:include page="../component/page_bar.jsp"></jsp:include>
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
