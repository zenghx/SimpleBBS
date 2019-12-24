<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/22
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="Bottom">
    <div class="content">
        <div class="inner">
            <div class="sep10"></div>
            SimpleBBS Version 1.0
            <div class="sep5"></div>
            <span class="small fade">
                Style Originated From <a href="https://www.v2ex.com"> V2EX</a>(Way to Explore)
            </span>
            <div class="sep10"></div>
        </div>
    </div>
</div>
<script>
    function load_post_page() {
        let param = window.location.href.split('/').pop();
        let reg = new RegExp("^[0-9]*$");
        param = param.split("?");
        param = param[0];
        let type;
        if (param == "")
            type = "index";
        else if (reg.test(param))
            type = "section";
        else type = "user";
        $.ajax({
            url: "${pageContext.request.contextPath}/post_count?type=" + type +
                "&value=" + param,
            type: "get",
            contentType: "text/json;charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data.status == 200) {
                    let count_template;
                    if (data.count == 0) {
                        count_template = '<div class="cell" id="count"> <span class="gray">还没有帖子</span></div>';
                    } else {
                        count_template = '<div class="cell" id="count"> <span class="gray" >共' + data.count + '篇帖子</span></div><img id="loading" src="https://zcdn.yce.ink/img/loading.gif"/>';
                    }
                    $("#post_content").html(count_template);
                    total_pages = Math.ceil(data.count / page_size);
                    let flag = total_pages < 1 ? 1 : total_pages;
                    if (flag == 1) {
                        $("#page_bar").remove();
                        return;
                    }
                    $("#page_count").text(page + "/" + total_pages);
                    console.log(data.count);
                }
            },
            error: function (data) {
                console.log("err" + data);
            }
        });
    }

    function load_posts() {
        let param = window.location.href.split('/').pop();
        let reg = new RegExp("^[0-9]*$");
        param = param.split("?");
        param = param[0];
        let type;
        if (param == "")
            type = "index";
        else if (reg.test(param))
            type = "section";
        else type = "user";
        $.when($.ajax({
            url: "${pageContext.request.contextPath}/glance_post?type=" + type +
                "&value=" + param + "&page=" + page + "&pageSize=" + page_size,
            type: "get",
            contentType: "text/json;charset=UTF-8",
            dataType: "json"
        })).then(function (data) {
            if (data.status == 200) {
                let posts = data.posts;
                let count = data.count;
                $(posts).each(function (i) {
                    $.when($.ajax({
                            url: "${pageContext.request.contextPath}/user/" + posts[i].author,
                            type: "get",
                            contentType: "text/json;charset=UTF-8",
                            dataType: "json"
                        }),
                        $.ajax({
                            url: "${pageContext.request.contextPath}/get_section_name/" + posts[i].section_id,
                            type: "get",
                            contentType: "text/json;charset=UTF-8",
                            dataType: "json"
                        }),
                        $.ajax({
                            url: "${pageContext.request.contextPath}/comment_count?type=post&value=" + posts[i].post_id,
                            type: "get",
                            contentType: "text/json;charset=UTF-8",
                            dataType: "json"
                        })).done(
                        function (usr_data, section_data, comment_count) {
                            let post_html = '<div class="cell item" style="">' +
                                '<table cellpadding="0" cellspacing="0" border="0" width="100%">' +
                                '<tbody><tr>' +
                                '<td width="48" valign="top" align="center"><a href="${pageContext.request.contextPath}/member/' + usr_data[0].user_name + '"><img src="' + usr_data[0].avatar_url + '?imageView2/1/w/48/h/48" class="avatar" border="0" align="default"></a></td>' +
                                '<td width="10"></td>' +
                                '<td width="auto" valign="middle"><span class="item_title"><a href="${pageContext.request.contextPath}/post/' + posts[i].post_id + '" class="topic-link">' + posts[i].title + '</a></span>' +
                                '<div class="sep5"></div>' +
                                '<span class="topic_info"><div class="votes"></div><a class="node" href="${pageContext.request.contextPath}/section/' + posts[i].section_id + '">' + section_data[0].section_name + '</a> &nbsp;•&nbsp; <strong><a href="${pageContext.request.contextPath}/member/' + usr_data[0].user_name + '">' + usr_data[0].user_name + '</a></strong> &nbsp;•&nbsp; ' + posts[i].post_time + ' &nbsp; </span>' +
                                '</td>' +
                                '<td width="70" align="right" valign="middle">' +
                                '<a href="${pageContext.request.contextPath}/post/' + posts[i].post_id + '" class="count_livid">' + comment_count[0].count + '</a>' +
                                '</td> </tr> </tbody></table></div>';
                            $("#count").after(post_html);
                            if (i == count - 1)
                                $("#loading").remove();
                        }).catch(function (data) {
                        console.log(data.statusText);
                    })
                });
            } else console.log(data);
        }).catch(function (data) {
            console.log(data.statusText)
        })
    }

    function activate_page_button(target) {
        target.removeClass("disable_now");
        target.on("mouseover", function () {
            target.addClass("hover_now");
        });
        target.on("mousedown", function () {
            target.addClass("active_now");
        });
        target.on("mouseleave", function () {
            target.removeClass("hover_now");
            target.removeClass("active_now");
        });
    }

    function deactivate_page_button(target) {
        target.off("mouseover");
        target.off("mousedown");
        target.off("mouseleave");
        target.removeClass('hover_now');
        target.removeClass("active_now");
        target.addClass("disable_now");
    }

    function next_page() {
        if (page == total_pages) return;
        let previous = $("#previous");
        let next = $("#next");
        if (page == 1) {
            activate_page_button(previous);
        }
        if (page + 1 == total_pages) {
            deactivate_page_button(next);
        }
        page = page + 1;
        if (target == "post") {
            load_posts();
            load_post_page();
        } else if (target == "comment") {
            load_comments();
            load_page();
        }
    }

    function previous_page() {
        let previous = $("#previous");
        let next = $("#next");
        if (page == 1) return;
        if (page == total_pages) {
            activate_page_button(next);
        }
        if (page - 1 == 1) {
            deactivate_page_button(previous);
        }
        page = page - 1;
        if (target == "post") {
            load_posts();
            load_post_page();
        } else if (target == "comment") {
            load_comments();
            load_page();
        }
    }
</script>
