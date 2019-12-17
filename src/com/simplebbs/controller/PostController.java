package com.simplebbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {
    @RequestMapping("/post")
    public String post(){
        return "post";
    }
}
