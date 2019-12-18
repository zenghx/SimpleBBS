package com.simplebbs.dao;

import com.simplebbs.po.Comments;
import org.apache.ibatis.annotations.Param;

public interface CommentDao {
    Comments findCommentByPost(@Param("post_id") long postId);
    Comments findCommentByUser(String username);
    Integer newComment(Comments comment);
}
