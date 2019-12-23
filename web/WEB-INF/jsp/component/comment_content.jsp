<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/23
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
    <div id="comments">
        <img src="https://zcdn.yce.ink/img/loading.gif"/>
    </div>
    <jsp:include page="../component/page_bar.jsp"></jsp:include>
</div>
<script>
    let page = 1;
    let page_size = 5;
    let total_pages;
    let target = "comment";

    function load_comments() {
        let param = window.location.href.split('/').pop();
        let reg = new RegExp("^[0-9]*$");
        param = param.split("?");
        param = param[0];
        let type;
        if (reg.test(param))
            type = "post";
        else type = "user";
        $.when($.ajax({
                url: "${pageContext.request.contextPath}/comment?targetType=" + type +
                    "&target=" + param + "&page=" + page + "&pageSize=" + page_size,
                type: "get",
                contentType: "text/json;charset=UTF-8",
                dataType: "json"
            }
        )).then(function (data) {
            if (data.status == 200) {
                let count = data.count;
                if (count != 0) {
                    let comments_content = data.comments;
                    $(comments_content).each(function (i) {
                        $.when($.ajax({
                            url: "${pageContext.request.contextPath}/user/" + comments_content[i].user_id,
                            type: "get",
                            contentType: "text/json;charset=UTF-8",
                            data: "json"
                        })).then(function (user_data) {
                            if (user_data.status == 200) {
                                let comments_html = '   <div id="c_' + comments_content[i].comment_id + '" class="cell">\n' +
                                    '                    <table cellpadding="0" cellspacing="0" border="0" width="100%">\n' +
                                    '                    <tbody>\n' +
                                    '                    <tr>\n' +
                                    '                    <td width="48" valign="top" align="center"><img src="' + user_data.avatar_url + '?imageView2/1/w/48/h/48" class="avatar" border="0" align="default"></td>\n' +
                                    '                    <td width="10" valign="top"></td>\n' +
                                    '                    <td width="auto" valign="top" align="left">\n' +
                                    '                <div class="sep3"></div>\n' +
                                    '                    <strong><a href="${pageContext.request.contextPath}/member/' + user_data.user_name + '" class="dark">' + user_data.user_name + '</a></strong>&nbsp; &nbsp;<span class="ago">' + comments_content[i].comment_time + '</span>\n' +
                                    '                <div class="sep5"></div>\n' +
                                    '                    <div class="reply_content">' + comments_content[i].content + '</div>\n' +
                                    '                </td>\n' +
                                    '                </tr>\n' +
                                    '                </tbody>\n' +
                                    '                </table>\n' +
                                    '                </div>';
                                $("#count").after(comments_html);
                                if (i == count - 1)
                                    $("#loading").remove();
                            } else console.log("get user " + user_id + " info failed.");
                        }).catch(function (data) {
                            console.log(data.statusText);
                        })
                    });

                } else {
                    let count_template = '<div class="cell"> <span class="gray">还没有回复</span></div>';
                    $("#comments").html(count_template);
                }
            }
        })
    }
    function load_page() {
        let param = window.location.href.split('/').pop();
        let reg = new RegExp("^[0-9]*$");
        param = param.split("?");
        param = param[0];
        let type;
        if (reg.test(param))
            type = "post";
        else type = "user";
        $.ajax({
            url: "${pageContext.request.contextPath}/comment_count?type=" + type +
                "&value=" + param,
            type: "get",
            contentType: "text/json;charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data.status == 200) {
                    let count_template = '<div class="cell" id="count"> <span class="gray" >' + data.count + '条回复</span></div><img id="loading" src="https://zcdn.yce.ink/img/loading.gif"/>';
                    $("#comments").html(count_template);
                    total_pages = Math.ceil(data.count / page_size);
                    let flag = total_pages < 1 ? 1 : total_pages;
                    if (flag == 1) {
                        $("#page_bar").remove();
                        return;
                    }
                    $("#page_count").text(page + "/" + total_pages);
                }
            },
            error: function (data) {
                console.log("err" + data);
            }
        });
    }
</script>
