package com.simplebbs.service;

import com.simplebbs.po.UserInfo;

public interface UserService {
    void addUser(UserInfo user);
    UserInfo login(String username,String password);
    UserInfo findUserByName(String username);
}
