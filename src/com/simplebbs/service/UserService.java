package com.simplebbs.service;

import com.simplebbs.po.UserInfo;
import com.simplebbs.po.UserPrivilege;

public interface UserService {
    Integer addUser(UserInfo user);

    Integer UpdateUserPrivilege(int user_id, boolean canpost, boolean cancomment, boolean admin);

    UserInfo login(String username, String password);

    UserInfo findUserByName(String username);

    UserInfo findUserById(int id);

    UserPrivilege FindUserPrivilege(int user_id);

    Integer AddUserPrivilege(int user_id, boolean canpost, boolean cancomment, boolean admin);

    Integer updateUserInfo(UserInfo user);
}
