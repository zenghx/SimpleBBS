package com.simplebbs.service.impl;

import com.simplebbs.dao.PersonDao;
import com.simplebbs.po.Posts;
import com.simplebbs.po.UserInfo;
import com.simplebbs.po.Comments;
import com.simplebbs.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService{
    private PersonDao personDao;
    @Autowired
    void setPersonDao(PersonDao personDao){
        this.personDao=personDao;
    }

    @Override
    public UserInfo FindUserInfobyid(int userId){
        if (userId != 0)
            return personDao.FindUserInfobyid(userId);
        else
            return null;
    }

    @Override
    public Posts FindUserPosts(int userId){
        if (userId != 0)
            return personDao.FindUserPosts(userId);
        else
            return null;
    }

    @Override
    public Comments FindUserComment(int userId){
        if (userId != 0)
            return personDao.FindUserComment(userId);
        else
            return null;
    }
}
