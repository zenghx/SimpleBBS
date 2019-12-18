package com.simplebbs.service;

import com.simplebbs.po.Posts;

import java.util.Date;

public interface PostService {
    Posts galancePostById(Long postId);
    Posts readPostById(Long postId);
    Integer newPost(Posts post);
    Integer delPost(Long postId);
    Integer setCommentStatus(Long postId,Boolean allowComment);
}
