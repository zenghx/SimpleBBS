package com.simplebbs.dao;

import com.simplebbs.po.Posts;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface PostDao {
    Posts galancePostById(Long postId);
    Posts readPostById(Long postId);
    Integer newPost(Posts post);
    Integer delPost(Long postId);
    Integer setCommentStatus(@Param("postId") Long postId,@Param("allowCommment") Boolean allowComment);

}
