package com.simplebbs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplebbs.po.Comments;
import com.simplebbs.po.Report;
import com.simplebbs.po.UserInfo;
import com.simplebbs.service.CommentService;
import com.simplebbs.service.PostService;
import com.simplebbs.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    private CommentService commentService;
    private PostService postService;
    private ReportService reportService;

    @Autowired
    void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/comment", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public Object getCommentById(String targetType, String target, int page, int pageSize) {
        int start = (page - 1) * pageSize;
        int offset = pageSize;
        String commentJson;
        ObjectMapper mapper = new ObjectMapper();
        List<Comments> commentsList;
        try {
            if (targetType.equals("post")) {
                commentsList = commentService.findCommentByPost(Long.parseLong(target), start, offset);
            } else if (targetType.equals("user")) {
                commentsList = commentService.findCommentByUser(target, start, offset);
            } else return "{\"status\":404,\"msg\":\"not found\"}";
            commentJson = mapper.writeValueAsString(commentsList);
            return "{\"status\":200,\"comments\":" + commentJson +
                    ",\"count\":" + commentsList.size() + "}";
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{\"status\":500,\"msg\":\"服务器错误\"}";
    }

    @RequestMapping(value = "/new_comment",method = RequestMethod.POST)
    @ResponseBody
    public Object newComment(@RequestBody Comments comment, HttpSession session){
        UserInfo user=(UserInfo) session.getAttribute("USER_SESSION");
        if(user==null)
            return "{\"status\":401,\"please sign in\"}";
        int result=commentService.newComment(user.getUser_id(),comment.getPost_id(),comment.getContent(),
                0,0,new Date());
        if(result>0)
            return "{\"status\":200,\"msg\":\"succeed\"}";
        else return "{\"status\":500,\"msg\":\"fail\"}";
    }

    @RequestMapping(value = "/comment_count", method = RequestMethod.GET)
    @ResponseBody
    public Object getCommentCount(String type, String value) {
        int count;
        if (type.equals("post")) {
            count = commentService.getCommentsCountByPost(Long.parseLong(value));
        } else if (type.equals("user")) {
            count = commentService.getCommentsCountByUser(value);
        } else return "{\"status\":400,\"msg\":\"Bad Request\"}";
        return "{\"status\":200,\"count\":" + count + "}";
    }


    @RequestMapping(value = "/comment_report",method = RequestMethod.POST,produces =  "text/json;charset=UTF8")
    @ResponseBody
    public Integer AddCommentReport(@RequestBody Report report){
        if (postService.readPostById(report.getPost_id())!=null){
            reportService.AddCommentReport(report.getPost_id(), report.getComment_id(),report.getReporter(),
                    report.getReport_reason(),report.getReport_time());
            return 1;
        }
        else return -1;
    }

}
