package com.simplebbs.controller;

import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.simplebbs.po.UserInfo;
import com.simplebbs.po.UserPrivilege;
import com.simplebbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService=userService;
    }

    @RequestMapping(value="/sign_in",method = RequestMethod.GET)
    public String signIn(){
        return "page/sign_in";
    }

    @RequestMapping(value = "/sign_in",method = RequestMethod.POST)
    public String doSignIn(String username, String password, Model model, HttpSession session){
        UserInfo foundUser=userService.login(username,password);
        if(foundUser!=null){
            session.setAttribute("USER_SESSION",foundUser);
            return "page/index";
        }
        model.addAttribute("msg","用户名或密码错误，请重新登录！");
        return "page/sign_in";
    }

    @RequestMapping(value = "/index")
    public String toIndex(){
        return "page/index";
    }

    @RequestMapping("/sign_out")
    public String logout(HttpSession session){
        session.invalidate();
        return "page/index";
    }
    @RequestMapping(value = "/sign_up",method = RequestMethod.GET)
    public String signUp(){
        return "page/sign_up";
    }

    @RequestMapping(value = "/sign_up",method = RequestMethod.POST)
    @ResponseBody
    public String toSignUp(@RequestBody UserInfo user){
        UserInfo foundUser=userService.findUserByName(user.getUser_name());
        if(foundUser!=null)
            return "{\"result\":\"failed\"}";
        else {
            userService.addUser(user);
            return "{\"result\":\"succeed\"}";
        }
    }

    @RequestMapping(value = "/user/{user_id}",method = RequestMethod.GET,produces ="text/json;charset=UTF-8" )
    @ResponseBody
    public Object getUserinfo(@PathVariable("user_id")int user_id){
        UserInfo user;
        UserPrivilege user_pri;
        if(user_id>0){
            user = userService.findUserById(user_id);
            user_pri = userService.FindUserPrivilege(user_id);
            return "{\"status\":200,\"user_name\":\""+user.getUser_name()+"\""+user.getAvatar_url()+"\""
                    +user.getBirthday()+"\""+user.getGender()+"\""+user_pri.isAble_comment()+"\""
                    +user_pri.isAble_post()+"\""+user_pri.isAdmin()+"\"";
        }
        else
            return null;
    }
}
