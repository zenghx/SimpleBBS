package com.simplebbs.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplebbs.po.UserInfo;
import com.simplebbs.po.UserPrivilege;
import com.simplebbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

@Controller
public class UserController {
    private String signReferer;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public String signIn(HttpServletRequest request) {
        signReferer = request.getHeader("Referer");
        return "page/sign_in";
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public String doSignIn(String username, String password, Model model, HttpSession session, HttpServletRequest request) {
        UserInfo foundUser = userService.login(username, password);
        if (foundUser != null) {
            session.setAttribute("USER_SESSION", foundUser);
            if (signReferer == null)
                signReferer = request.getHeader("Referer");
            if (signReferer != null && !signReferer.contains("sign_up"))
                return "redirect:" + signReferer;
            else return "redirect:/";
        }
        model.addAttribute("msg", "用户名或密码错误，请重新登录！");
        return "page/sign_in";
    }

    @RequestMapping(value = "/index")
    public String toIndex() {
        return "page/index";
    }

    @RequestMapping("/sign_out")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
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
            int usrResult = userService.addUser(user);
            if (usrResult > 0) {
                return "{\"status\":200,\"msg\":\"succeed\"}";
            } else return "{\"status\":500,\"msg\":\"failed\"}";
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
                if (user.getBirthday() != null)
                    bd = sdf.format(user.getBirthday());
                else bd = "未设置";
                return "{" +
                        "\"status\":200," +
                        "\"user_name\":\"" + user.getUser_name() + "\"," +
                        "\"avatar_url\":\"" + user.getAvatar_url() + "\"," +
                        "\"birthday\":\"" + bd + "\"," +
                        "\"gender\":" + user.getGender() + "," +
                        "\"is_able_comment\":" + user_pri.isAble_comment() + "," +
                        "\"is_able_post\":" + user_pri.isAble_post() + "," +
                        "\"is_admin\":" + user_pri.isAdmin() +
                        "}";
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
            if (current_user_Privilege.isAdmin()) {
                if (userpriv != null && userpriv.getUser_id() > 0) {
                    boolean canpost = userpriv.isAble_post();
                    boolean cancomment = userpriv.isAble_comment();
                    boolean isadmin = userpriv.isAdmin();
                    if (!canpost) {
                        canpost = false;
                    }
                    if (!cancomment) {
                        cancomment = false;
                    }
                    if (!isadmin) {
                        isadmin = false;
                    }
                    int result = userService.UpdateUserPrivilege(userpriv.getUser_id(), canpost, cancomment,
                            isadmin);
                    if (result > 0)
                        return "{\"status\":200,\"msg\":\"Updated\"}";
                    else return "{\"status\":404,\"msg\":\"Not Found\"}";
                } else return "{\"status\":400,\"msg\":\"Bad Request\"}";
            } else return "{\"status\":403,\"msg\":\"Forbidden\"}";

        } else return "{\"status\":401,\"msg\":\"Unauthorized\"}";
    }

    @RequestMapping(value = "/member/{username}")
    public String memberIndex(@PathVariable("username") String username, Model model) {
        UserInfo user = userService.findUserByName(username);
        if (user == null)
            return "page/404";
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String birthday = null;
            if (user.getBirthday() != null)
                birthday = sdf.format(user.getBirthday());
            model.addAttribute("user", user);
            model.addAttribute("birthday", birthday);
            return "page/member";
        }
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String toSettings(Model model, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (user == null)
            return "redirect:page/sign_in";
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String birthday = null;
            if (user.getBirthday() != null)
                birthday = sdf.format(user.getBirthday());
            model.addAttribute("user", user);
            model.addAttribute("birthday", birthday);
            return "page/settings";
        }
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String Settings(UserInfo updatedUser, HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (user == null)
            return "redirect:page/sign_in";
        else {
            updatedUser.setUser_id(user.getUser_id());
            int result = userService.updateUserInfo(updatedUser);
            if (result > 0) {
                updatedUser = userService.findUserById(updatedUser.getUser_id());
                session.setAttribute("USER_SESSION", updatedUser);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String birthday = null;
                if (user.getBirthday() != null)
                    birthday = sdf.format(updatedUser.getBirthday());
                model.addAttribute("user", updatedUser);
                model.addAttribute("birthday", birthday);
                model.addAttribute("msg", "修改个人信息成功。");
            } else model.addAttribute("msg", "由于服务器端错误，修改失败，请稍后重试。");
            return "page/settings";
        }
    }

    @RequestMapping(value = "/settings/password", method = RequestMethod.POST, produces = "text/json;charset=UTF8")
    @ResponseBody
    public Object pwdSettings(@RequestBody String request, HttpSession session) {
        ObjectMapper mapper = new ObjectMapper();
        UserInfo current_usr = (UserInfo) session.getAttribute("USER_SESSION");
        if (current_usr == null)
            return "{\"status\":401,\"msg\":\"登录已失效，请刷新页面。\"}";
        UserInfo updatedUsr = new UserInfo();
        try {
            JsonNode node = mapper.readTree(request);
            String current_pwd = node.path("password_current").asText();
            if (current_pwd.equals(current_usr.getPwd_hash())) {
                updatedUsr.setPwd_hash(node.path("password_new").asText());
                updatedUsr.setUser_id(current_usr.getUser_id());
                updatedUsr.setGender(current_usr.getGender());
                userService.updateUserInfo(updatedUsr);
                session.invalidate();
                return "{\"status\":200,\"msg\":\"修改密码成功！\"}";
            } else return "{\"status\":401,\"msg\":\"当前密码有误，请查证。\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"status\":500,\"msg\":\"服务器出错。\"}";
    }
}

