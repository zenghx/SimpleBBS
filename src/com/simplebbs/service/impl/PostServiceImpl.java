package com.simplebbs.service.impl;

import com.simplebbs.dao.PostDao;
import com.simplebbs.po.Posts;
import com.simplebbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PostServiceImpl implements PostService {
    private PostDao postDao;
    @Autowired
    void setPostDao(PostDao postDao){this.postDao=postDao;}
    @Override
    public Posts galancePostById(Long postId) {
        return postDao.galancePostById(postId);
    }

    @Override
    public Posts readPostById(Long postId) {
        return postDao.readPostById(postId);
    }

    @Override
    @Transactional
    public Integer newPost(Posts post) {
        return postDao.newPost(post);
    }

    @Override
    @Transactional
    public Integer delPost(Long postId) {
        return postDao.delPost(postId);
    }

    @Override
    @Transactional
    public Integer setCommentStatus(Long postId, Boolean allowComment) {
        return postDao.setCommentStatus(postId,allowComment);
    }
}
