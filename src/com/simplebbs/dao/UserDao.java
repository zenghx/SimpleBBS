package com.simplebbs.dao;

import com.simplebbs.po.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    void addUser(@Param("username") String username,@Param("password") String password);
    UserInfo findUserByName(String username);
    UserInfo findUserById(int id);
}
