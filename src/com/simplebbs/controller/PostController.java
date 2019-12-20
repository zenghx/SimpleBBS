package com.simplebbs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
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
            return "page/post";
        }
        else return "page/404";
    }

    @RequestMapping(value = "/new_post",method = RequestMethod.GET)
    public String toNewPost(Model model){
        List allSections=postService.allSections();
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
        else return "{\"status\":404,\"msg\":\"fail\"}";
    }

    @RequestMapping(value = "/glance_post",method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    @ResponseBody
    public Object glancePost(@RequestBody String request){
        ObjectMapper mapper=new ObjectMapper();
        JsonNode node= null;
        try {
            node = mapper.readTree(request);
            String type=node.path("type").asText();
            int page=node.path("page").asInt();
            int pageSize=node.path("page_size").asInt();
            int sectionId;
            int userId;
            if(type.equals("index")){
                sectionId=0;
                userId=0;
            }
            else if (type.equals("user")){
                String username= node.path("value").asText();
                userId=userService.findUserByName(username).getUser_id();
                sectionId=0;
            }
            else if(type.equals("section")){
                sectionId=node.path("value").asInt();
                userId=0;
            }
            else return "{\"status\":404,\"msg\":\"not found\"}";
            List<Posts> result=postService.glancePost(sectionId,userId,page,pageSize);
            int postCount=postService.getPostCount(sectionId,userId);
            if(result!=null&&postCount!=0)
                return "{\"status\":200,\"posts\":"+mapper.writeValueAsString(result)+",\"count\":"+postCount+"}";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
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
    public String showSection(@PathVariable("id")int sectionId,HttpSession session,Model model){
        Section section=postService.findSectionById(sectionId);
        UserInfo user=(UserInfo) session.getAttribute("USER_SESSION");
        boolean isSignedIn=(user!=null);
        model.addAttribute("SectionName",section.getSection_name());
        return "page/section";
    }
}
