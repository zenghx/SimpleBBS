<%--
  Created by IntelliJ IDEA.
  User: zhiku
  Date: 2019/12/18
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人界面</title>
</head>
<body>
<div class="tab">
    <button class="tablinks" onclick="openCity(event, '信息')">London</button>
    <button class="tablinks" onclick="openCity(event, '帖子')">Paris</button>
    <button class="tablinks" onclick="openCity(event, '评论')">Tokyo</button>
</div>

<div id="信息" class="tabcontent">
    <h3>London</h3>
    <p>个人信息</p>
</div>

<div id="帖子" class="tabcontent">
    <h3>帖子</h3>
    <p>所发的帖子</p>
</div>

<div id="Tokyo" class="tabcontent">
    <h3>评论</h3>
    <p>对帖子的评论和点亮、点灭</p>
</div>
<script>
    function openCity(evt, cityName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " active";
    }
</script>


</body>
</html>
