package com.simplebbs.controller;

import com.simplebbs.po.UserInfo;
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
        return "sign_in";
    }
    @RequestMapping(value = "/sign_in",method = RequestMethod.POST)
    public String doSignIn(String username, String password, Model model, HttpSession session){
        UserInfo foundUser=userService.login(username,password);
        if(foundUser!=null){
            session.setAttribute("USER_SESSION",foundUser);
            return "redirect:index";
        }
        model.addAttribute("msg","用户名或密码错误，请重新登录！");
        return "sign_in";
    }
    @RequestMapping(value = "/index")
    public String toIndex(){
        return "index";
    }
    @RequestMapping("/sign_out")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:index";
    }
    @RequestMapping(value = "/sign_up",method = RequestMethod.GET)
    public String signUp(){
        return "sign_up";
    }

    @RequestMapping(value = "/sign_up",method = RequestMethod.POST)
    @ResponseBody
    public String toSignUp(@RequestBody UserInfo user){
        UserInfo foundUser=userService.findUserByName(user.getUser_name());
        if(foundUser!=null)
            return "false";
        else {
            userService.addUser(user);
            return "true";
        }
    }
}
