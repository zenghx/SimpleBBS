package com.simplebbs.dao;

import com.simplebbs.po.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserDao {
    Integer addUser(UserInfo user);

    UserInfo findUserByName(String username);

    UserInfo findUserById(int id);

    Integer updateUserInfo(@Param("userId") int userId, @Param("gender") Integer gender,
                           @Param("password") String password,
                           @Param("birthday") Date birthday, @Param("avatar") String avatar);
}
