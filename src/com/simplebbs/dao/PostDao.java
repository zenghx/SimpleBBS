package com.simplebbs.dao;

import com.simplebbs.po.Posts;
import com.simplebbs.po.Section;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostDao {
    Posts galancePostById(long postId);
    Posts readPostById(long postId);
    Integer newPost(Posts post);
    Integer delPost(long postId);
    Integer setCommentStatus(@Param("postId") long postId,@Param("allowCommment") boolean allowComment);
    List<Section> showAllSections();
}
