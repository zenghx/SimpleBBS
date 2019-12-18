package com.simplebbs.dao;

import com.simplebbs.po.Posts;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface PostDao {
    Posts galancePostById(Long postId);
    Posts readPostById(Long postId);
    Posts newPost(Integer author, String content, String title,
                         Boolean allowComment, Date postTime,Integer likes,
                         String section,Integer dislikes);
    Boolean delPost(Long postId);
    Boolean setCommentStatus(Long postId,Boolean allowComment);

}
