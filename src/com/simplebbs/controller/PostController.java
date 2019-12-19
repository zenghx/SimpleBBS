package com.simplebbs.controller;

import com.simplebbs.po.Comments;
import com.simplebbs.po.Posts;
import com.simplebbs.po.UserInfo;
import com.simplebbs.service.CommentService;
import com.simplebbs.service.PostService;
import com.simplebbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {
    private PostService postService;
    private UserService userService;
    @Autowired
    void setPostService(PostService postService){
        this.postService=postService;
    }
    @Autowired
    void setUserService(UserService userService){
        this.userService=userService;
    }

    @RequestMapping("/post/{id}")
    public String post(@PathVariable("id") long postID, Model model){
        Posts post=postService.readPostById(postID);
        if(post!=null){
            int uid=post.getAuthor();
            UserInfo postUser=userService.findUserById(uid);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
            model.addAttribute("title",post.getTitle());
            model.addAttribute("content",post.getContent());
            model.addAttribute("postTime",format1.format(post.getPost_time()));
            model.addAttribute("username",postUser.getUser_name());
            model.addAttribute("avatar",postUser.getAvatar_url());

            return "post";
        }
        else return "404";
    }

    @RequestMapping(value = "/new_post",method = RequestMethod.GET)
    public String toNewPost(Model model){
        List allSections=postService.allSections();
        model.addAttribute("allSections",allSections);
        return "new_post";
    }

    @RequestMapping(value = "/new_post",method = RequestMethod.POST)
    @ResponseBody
    public String newPost(@RequestBody Posts newPost, HttpSession session){
        UserInfo user=(UserInfo)session.getAttribute("USER_SESSION");
        if(user==null)
            return "{\"status\":\"please sign in first\"}";
        else newPost.setAuthor(user.getUser_id());
        newPost.setLikes(0);
        newPost.setDislikes(0);
        newPost.setAllow_comment(true);
        newPost.setPost_time(new Date());
        long result=postService.newPost(newPost.getAuthor(),newPost.getContent(),newPost.getTitle(),
                newPost.isAllow_comment(),newPost.getPost_time(),newPost.getLikes(),
                newPost.getSection_id(),newPost.getDislikes());
        if(result>0)
            return "{\"post_id\":"+result+",\"status\":\"succeed\"}";
        else return "{\"status\":\"fail\"}";
    }

}
