<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/17
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<div class="box" style="border-bottom: 0;">
    <div class="header">
        <div class="fr">
            <a href="${pageContext.request.contextPath}/member/${postUser.user_name}"><img src="${avatar}" class="avatar" border="0" align="default"></a>
        </div>
        <a href="${pageContext.request.contextPath}/">SimpleBBS</a> <span class="chevron">&nbsp;›&nbsp;</span> <a href="${pageContext.request.contextPath}/section/${section_id}">${section_name}</a>
        <div class="sep10"></div>
        <h1>${title}</h1>
        <small class="gray"><a href="${pageContext.request.contextPath}/member/${postUser.user_name}">${postUser.user_name}</a> · ${postTime}</small>
    </div>
    <div class="cell">
        <div class="topic_content">${content}</div>
    </div>
</div>

