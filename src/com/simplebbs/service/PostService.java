package com.simplebbs.service;

import com.simplebbs.po.Posts;

import java.util.Date;

public interface PostService {
    Posts galancePostById(long postId);
    Posts readPostById(long postId);
    long newPost(int author, String content, String title,
                boolean allowComment, Date postTime,int likes,
                int section_id,int dislikes);
    Integer delPost(long postId);
    Integer setCommentStatus(long postId,boolean allowComment);
}
