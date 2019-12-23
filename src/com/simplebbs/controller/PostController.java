package com.simplebbs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplebbs.po.Posts;
import com.simplebbs.po.Section;
import com.simplebbs.po.UserInfo;
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
    public String readPost(@PathVariable("id") long postID, Model model){
        Posts post=postService.readPostById(postID);
        if(post!=null){
            int uid=post.getAuthor();
            UserInfo postUser=userService.findUserById(uid);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
            model.addAttribute("title",post.getTitle());
            model.addAttribute("content",post.getContent());
            model.addAttribute("postTime",format1.format(post.getPost_time()));
            model.addAttribute("postUser",postUser);
            model.addAttribute("avatar",postUser.getAvatar_url());
            model.addAttribute("section_id",post.getSection_id());
            model.addAttribute("section_name",postService.findSectionById(post.getSection_id()).getSection_name());
            return "page/post";
        }
        else return "page/404";
    }

    @RequestMapping(value = "/new_post",method = RequestMethod.GET)
    public String toNewPost(Model model){
        List<Section> allSections = postService.allSections();
        model.addAttribute("allSections",allSections);
        return "page/new_post";
    }

    @RequestMapping(value = "/new_post",method = RequestMethod.POST)
    @ResponseBody
    public String newPost(@RequestBody Posts newPost, HttpSession session){
        UserInfo user=(UserInfo)session.getAttribute("USER_SESSION");
        if(user==null)
            return "{\"status\":401,\"msg\":\"please sign in first\"}";
        else newPost.setAuthor(user.getUser_id());
        newPost.setLikes(0);
        newPost.setDislikes(0);
        newPost.setAllow_comment(true);
        newPost.setPost_time(new Date());
        long result=postService.newPost(newPost.getAuthor(),newPost.getContent(),newPost.getTitle(),
                newPost.isAllow_comment(),newPost.getPost_time(),newPost.getLikes(),
                newPost.getSection_id(),newPost.getDislikes());
        if(result>0)
            return "{\"post_id\":"+result+",\"status\":200}";
        else return "{\"status\":404,\"msg\":\"not found\"}";
    }

    @RequestMapping(value = "/glance_post", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public Object glancePost(String type, String value, int page, int pageSize) throws JsonProcessingException {
        int sectionId;
        int userId;
        int postCount;
        ObjectMapper mapper = new ObjectMapper();
        if (type.equals("index")) {
            sectionId = 0;
            userId = 0;
        } else if (type.equals("user")) {
            userId = userService.findUserByName(value).getUser_id();
            sectionId = 0;
        } else if (type.equals("section")) {
            sectionId = Integer.parseInt(value);
            userId = 0;
        } else return "{\"status\":404,\"msg\":\"not found\"}";
        List<Posts> result = postService.glancePost(sectionId, userId, page, pageSize);
        if (result != null) {
            postCount = result.size();
            return "{\"status\":200,\"posts\":" + mapper.writeValueAsString(result) + ",\"count\":" + postCount + "}";
        }
        return "{\"status\":404,\"msg\":\"not found\"}";

    }

    @RequestMapping(value = "/get_section_name/{id}",method=RequestMethod.GET,produces = "text/json;charset=UTF8")
    @ResponseBody
    public String getSectionName(@PathVariable("id") int sectionId){
        if(sectionId<=0)
            return "{\"status\":400,\"msg\":\"illegal parameter\"}";
        Section section=postService.findSectionById(sectionId);
        if(section!=null)
            return "{\"status\":200,\"section_name\":\""+section.getSection_name()+"\"}";
        return "{\"status\":404,\"msg\":\"not found\"}";
    }

    @RequestMapping("/section/{id}")
    public String showSection(@PathVariable("id") int sectionId, HttpSession session, Model model) {
        Section section = postService.findSectionById(sectionId);
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        boolean isSignedIn = (user != null);
        model.addAttribute("SectionName", section.getSection_name());
        return "page/section";
    }

    @RequestMapping(value = "/post_count", method = RequestMethod.GET)
    @ResponseBody
    public Object getPostCount(String type, String value) {
        int sectionId;
        int userId;
        if (type.equals("index")) {
            sectionId = 0;
            userId = 0;
        } else if (type.equals("section")) {
            userId = 0;
            sectionId = Integer.parseInt(value);
        } else if (type.equals("user")) {
            sectionId = 0;
            UserInfo user = userService.findUserByName(value);
            if (user != null)
                userId = user.getUser_id();
            else return "{\"status\":404,\"msg\":\"Not Found\"}";
        } else return "{\"status\":400,\"msg\":\"Bad Request\"}";
        return "{\"status\":200,\"count\":" + postService.getPostCount(sectionId, userId) + "}";
    }
}
