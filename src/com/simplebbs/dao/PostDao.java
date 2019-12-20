package com.simplebbs.dao;

import com.simplebbs.po.Posts;
import com.simplebbs.po.Section;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostDao {
    List<Posts> glancePost(@Param("sectionId") int sectionId,@Param("userId") long userId,
                                 @Param("start") int start,@Param("end") int end);//帖子概览
    Integer getPostCount(@Param("sectionId") int sectionId,@Param("userId") long userId);
    Posts readPostById(long postId);
    Integer newPost(Posts post);
    Integer delPost(long postId);
    Integer setCommentStatus(@Param("postId") long postId,@Param("allowCommment") boolean allowComment);
    List<Section> showAllSections();
    Section findSectionById(int sectionId);
}
