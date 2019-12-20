package com.simplebbs.service;

import com.simplebbs.po.Posts;
import com.simplebbs.po.Section;

import java.util.Date;
import java.util.List;

public interface PostService {
    List<Posts> glancePost(int sectionId,long userId,int page,int pageSize);
    Posts readPostById(long postId);
    long newPost(int author, String content, String title,
                boolean allowComment, Date postTime,int likes,
                int section_id,int dislikes);
    Integer delPost(long postId);
    Integer setCommentStatus(long postId,boolean allowComment);
    List<Section> allSections();
}
