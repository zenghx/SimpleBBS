package com.simplebbs.controller;

import com.simplebbs.po.Posts;
import com.simplebbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class PostController {
    private PostService postService;
    @Autowired
    void setPostService(PostService postService){
        this.postService=postService;
    }

    @RequestMapping("/post/{id}")
    public String post(@PathVariable("id") Long postID){
        return "post";
    }

    @RequestMapping(value = "/new_post",method = RequestMethod.GET)
    public String toNewPost(){
        return "new_post";
    }
    @RequestMapping(value = "/new_post",method = RequestMethod.POST)
    @ResponseBody
    public String newPost(@RequestBody Posts newPost){
        newPost.setLikes(0);
        newPost.setDislikes(0);
        newPost.setAllow_comment(true);
        newPost.setPost_time(new Date());
        int result=postService.newPost(newPost);
        Long posted_id=newPost.getPost_id();
        if(result>0)
            return "{post_id:"+posted_id+",status:succeed}";
        else return "{status:fail}";
    }
}
