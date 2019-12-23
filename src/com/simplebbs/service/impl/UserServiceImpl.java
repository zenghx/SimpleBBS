package com.simplebbs.service.impl;

import com.simplebbs.dao.UserDao;
import com.simplebbs.dao.UserPrivilegeDao;
import com.simplebbs.po.UserInfo;
import com.simplebbs.po.UserPrivilege;
import com.simplebbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {          //UserServiced的接口实现类
    private UserDao userDao;
    private UserPrivilegeDao userPrivilegeDao;

    @Autowired
    void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    void setUserPrivilegeDao(UserPrivilegeDao userPrivilegeDao) {
        this.userPrivilegeDao = userPrivilegeDao;
    }

    @Override
    @Transactional
    public Integer addUser(UserInfo user) {
        if (user != null && user.getUser_name() != null && user.getPwd_hash() != null) {
            int result = userDao.addUser(user);
            userPrivilegeDao.AddUserPrivilege(user.getUser_id(), true, true, false);
            return result;
        } else return -1;
    }

    @Override
    @Transactional
    public Integer UpdateUserPrivilege(int user_id, boolean canpost, boolean cancomment, boolean admin) {
        if (user_id > 0)
            return userPrivilegeDao.UpdateUserPrivilege(user_id, canpost, cancomment, admin);
        else return -1;
    }

    @Override
    public UserInfo login(String username, String password) {
        if (username!=null&&!username.isBlank()){
            UserInfo user=userDao.findUserByName(username);
        if(user!=null&& user.getPwd_hash().equals(password))
            return user;
        else return null;
        }
        else return null;
    }

    @Override
    public UserInfo findUserByName(String username) {
        if(username!=null&&!username.isBlank())
            return userDao.findUserByName(username);
        else return null;
    }

    @Override
    public UserInfo findUserById(int id) {
        if(id>0)
            return userDao.findUserById(id);
        else return null;
    }

    @Override
    public UserPrivilege FindUserPrivilege(int user_id) {
        if (user_id > 0)
            return userPrivilegeDao.FindUserPrivilege(user_id);
        else return null;
    }

    @Override
    @Transactional
    public Integer AddUserPrivilege(int user_id, boolean canpost, boolean cancomment, boolean admin) {
        if (user_id > 0)
            return userPrivilegeDao.AddUserPrivilege(user_id, canpost, cancomment, admin);
        else return -1;
    }

    @Override
    @Transactional
    public Integer updateUserInfo(UserInfo user) {
        if (user == null || user.getUser_id() <= 0)
            return -1;
        else return userDao.updateUserInfo(user.getUser_id(), user.getGender(),
                user.getPwd_hash(), user.getBirthday(), user.getAvatar_url());
    }

}
