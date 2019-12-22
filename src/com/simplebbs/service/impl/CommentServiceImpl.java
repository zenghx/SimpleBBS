package com.simplebbs.service.impl;

import com.simplebbs.dao.CommentDao;
import com.simplebbs.dao.UserDao;
import com.simplebbs.po.Comments;
import com.simplebbs.po.UserInfo;
import com.simplebbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;
    private UserDao userDao;
    @Autowired
    void setCommentDao(CommentDao commentDao){
        this.commentDao=commentDao;
    }
    @Autowired
    void setUserDao(UserDao userDao){this.userDao=userDao;}


    @Override
    public List<Comments> findCommentByPost(long postId,int start,int end) {
        if (postId!=0){
            return commentDao.findCommentByPost(postId,start,end);
        }
        else return null;
    }

    @Override
    public List<Comments> findCommentByUser(String username,int start,int end) {
        if(username!=null)
            return commentDao.findCommentByUser(username,start,end);
        return null;
    }


    @Override
    public Integer newComment(int userId, long postId, String content, int likes,
                              int dislikes, Date commentTime) {
        Comments comment=new Comments();
        if(userId<=0)
            return -1;
        comment.setUser_id(userId);
        if(postId<=0)
            return -1;
        if(content.isBlank())
            return -1;
        comment.setContent(content);
        comment.setPost_id(postId);
        comment.setLikes(likes);
        comment.setDislikes(dislikes);
        comment.setComment_time(commentTime);
        return commentDao.newComment(comment);
    }

    @Override
    public Integer getCommentsCountByPost(long postId) {
        if(postId<=0)
            return null;
        else return commentDao.getCommentsCountByPost(postId);
    }

    @Override
    public Integer getCommentsCountByUser(String username) {
        if(username==null)
            return null;
        else {
            UserInfo user=userDao.findUserByName(username);
            return commentDao.getCommentsCountByUser(user.getUser_id());
        }
    }


}
