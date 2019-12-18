package com.simplebbs.service.impl;

import com.simplebbs.dao.CommentDao;
import com.simplebbs.po.Comments;
import com.simplebbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;
    @Autowired
    void setCommentDao(CommentDao commentDao){
        this.commentDao=commentDao;
    }


    @Override
    public Comments findCommentByPost(long postId) {
        if (postId!=0)
            return commentDao.findCommentByPost(postId);
        else return null;
    }

    @Override
    public Comments findCommentByUser(String username) {
        if(username!=null)
            return commentDao.findCommentByUser(username);
        else return null;
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


}
