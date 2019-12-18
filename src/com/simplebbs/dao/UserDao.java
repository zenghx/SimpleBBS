package com.simplebbs.dao;

import com.simplebbs.po.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    void addUser(String username,String password);
    UserInfo findUserByName(String username);

}
