package com.simplebbs.service.impl;

import com.simplebbs.dao.UserDao;
import com.simplebbs.po.UserInfo;
import com.simplebbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    @Autowired
    void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public void addUser(UserInfo user) {
        userDao.addUser(user.getUser_name(),user.getPwd_hash());
    }

    @Override
    public UserInfo login(String username, String password) {
        UserInfo user=userDao.findUserByName(username);
        if(user!=null&& user.getPwd_hash().equals(password))
            return user;
        else return null;
    }
}
