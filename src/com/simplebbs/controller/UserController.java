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
import java.text.SimpleDateFormat;

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
            return "{\"status\":400,\"msg\":\"failed\"}";
        else {
            userService.addUser(user);
            return "{\"status\":200,\"msg\":\"succeed\"}";
        }
    }

    @RequestMapping(value = "/user/{user_id}",method = RequestMethod.GET,produces ="text/json;charset=UTF-8" )
    @ResponseBody
    public Object getUserInfo(@PathVariable("user_id")int user_id){
        UserInfo user;
        UserPrivilege user_pri;
        if(user_id>0) {
            user = userService.findUserById(user_id);
            user_pri = userService.FindUserPrivilege(user_id);
            if (user != null && user_pri != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                String bd;
                if(user.getBirthday()!=null)
                    bd=sdf.format(user.getBirthday());
                else bd="";
                String response= "{" +
                        "\"status\":200," +
                        "\"user_name\":\"" + user.getUser_name() + "\"," +
                        "\"avatar_url\":\"" + user.getAvatar_url() + "\"," +
                        "\"birthday\":" + bd + "," +
                        "\"gender\":" + user.getGender() + "," +
                        "\"is_able_comment\":" + user_pri.isAble_comment() + "," +
                        "\"is_able_post\":" + user_pri.isAble_post() + "," +
                        "\"is_admin\":" + user_pri.isAdmin() + "," +
                        "}";
                return response;
            }
        }
        return "{\"status\":404,\"msg\":\"not found\"}";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.POST,produces ="text/json;charset=UTF-8")
    @ResponseBody
    public Object UpdateUserPrivilege(@RequestBody UserPrivilege userpriv,HttpSession session){
        UserInfo current_user = (UserInfo) session.getAttribute("USER_SESSION");
        if (current_user!=null){
                UserPrivilege current_user_Privilege = userService.FindUserPrivilege(current_user.getUser_id());
                if (current_user_Privilege.isAdmin() == true){
                    if(userpriv!=null&&userpriv.getUser_id()>0) {
                        UserPrivilege mudiuserpriv = userService.FindUserPrivilege(userpriv.getUser_id());
                        boolean canpost = userpriv.isAble_post();
                        boolean cancomment = userpriv.isAble_comment();
                        boolean isadmin = userpriv.isAdmin();
                        if (canpost == mudiuserpriv.isAble_post()){
                            canpost = mudiuserpriv.isAble_post();
                        }
                        if (cancomment == mudiuserpriv.isAble_comment()){
                            cancomment = mudiuserpriv.isAble_comment();
                        }
                        if (isadmin == mudiuserpriv.isAdmin()){
                            isadmin = mudiuserpriv.isAdmin();
                        }
                        int result=userService.UpdateUserPrivilege(userpriv.getUser_id(), canpost,cancomment,
                                isadmin);
                        if(result>0)
                            return "{\"status\":200,\"msg\":\"Updated\"}";
                        else return "{\"status\":404,\"msg\":\"Not Found\"}";
                    }
                    else return "{\"status\":400,\"msg\":\"Bad Request\"}";
                }
                else return "{\"status\":403,\"msg\":\"Forbidden\"}";

            }
        else return "{\"status\":401,\"msg\":\"Unauthorized\"}";
        }

}

