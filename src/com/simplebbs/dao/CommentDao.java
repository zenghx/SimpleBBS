package com.simplebbs.dao;

import com.simplebbs.po.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {
    List<Comments> findCommentByPost(@Param("post_id") long postId,@Param("start") int start,@Param("end") int end);
    List<Comments> findCommentByUser(@Param("username") String username,@Param("start") int start,@Param("end") int end);
    Integer newComment(Comments comment);
    Integer getCommentsCountByPost(long postId);
    Integer getCommentsCountByUser(int userId);
}
