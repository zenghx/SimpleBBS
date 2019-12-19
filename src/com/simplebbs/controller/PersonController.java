package com.simplebbs.controller;

import com.simplebbs.po.UserInfo;
import com.simplebbs.po.Posts;
import com.simplebbs.service.PostService;
import com.simplebbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class PersonController {
    private PostService postService;
    @Autowired
    void setPostService(PostService postService){
        this.postService=postService;
    }
    private UserService userService;
    @Autowired
    void setUserService(PostService postService){ this.userService=userService; }

    @RequestMapping(value = "/person",method = RequestMethod.GET)
    public String toperson(){
        return "person";
    }

    @RequestMapping(value = "/person",method = RequestMethod.POST)
    public String showperson(@RequestBody Posts hisPost,UserInfo userinfo){
        String avatar_url = userinfo.getAvatar_url();
        String user_name = userinfo.getUser_name();
        return user_name;

    }
}

