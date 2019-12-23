package com.simplebbs.service.impl;

import com.simplebbs.dao.PostDao;
import com.simplebbs.po.Posts;
import com.simplebbs.po.Section;
import com.simplebbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostDao postDao;
    @Autowired
    void setPostDao(PostDao postDao){this.postDao=postDao;}


    @Override
    public List<Posts> glancePost(int sectionId,long userId, int page, int pageSize) {
        if (userId < 0 || sectionId < 0 || page < 1 || pageSize <= 0)
            return null;
        else return postDao.glancePost(sectionId, userId, (page - 1) * pageSize, pageSize);
    }

    @Override
    public Integer getPostCount(int sectionId, long userId) {
        if(userId<0||sectionId<0)
            return null;
        else return postDao.getPostCount(sectionId,userId);
    }


    @Override
    public Posts readPostById(long postId) {
        return postDao.readPostById(postId);
    }

    @Transactional
    @Override
    public long newPost(int author, String content, String title, boolean allowComment, Date postTime, int likes, int section_id, int dislikes) {
        if(author<=0)
            return -1;
        if(content.isBlank())
            return -1;
        if(title.isBlank())
            return -1;
        if(section_id<=0)
            return -1;
        Posts post=new Posts();
        post.setContent(content);
        post.setPost_time(postTime);
        post.setSection_id(section_id);
        post.setAuthor(author);
        post.setAllow_comment(allowComment);
        post.setDislikes(dislikes);
        post.setLikes(likes);
        post.setTitle(title);
        if(postDao.newPost(post)<0)
            return -1;
        else return post.getPost_id();
    }


    @Override
    @Transactional
    public Integer delPost(long postId) {
        return postDao.delPost(postId);
    }

    @Override
    @Transactional
    public Integer setCommentStatus(long postId, boolean allowComment) {
        return postDao.setCommentStatus(postId,allowComment);
    }

    @Override
    public List<Section> allSections() {
        return postDao.showAllSections();
    }

    @Override
    public Section findSectionById(int sectionId) {
        if(sectionId<=0)
            return null;
        else return postDao.findSectionById(sectionId);
    }
}
