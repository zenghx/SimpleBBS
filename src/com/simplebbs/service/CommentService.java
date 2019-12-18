package com.simplebbs.service;

import com.simplebbs.po.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CommentService {
    Comments findCommentByPost(@Param("post_id") long postId);
    Comments findCommentByUser(String username);
    Integer newComment(int userId, long postId, String content, int likes,
                       int dislikes, Date commentTime);
}
