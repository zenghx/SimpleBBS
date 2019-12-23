package com.simplebbs.service;

import com.simplebbs.po.Comments;

import java.util.Date;
import java.util.List;

public interface CommentService {
    List<Comments> findCommentByPost(long postId, int start, int offset);

    List<Comments> findCommentByUser(String username, int start, int offset);

    Integer newComment(int userId, long postId, String content, int likes,
                       int dislikes, Date commentTime);

    Integer getCommentsCountByPost(long postId);

    Integer getCommentsCountByUser(String username);
}
