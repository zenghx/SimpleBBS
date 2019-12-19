package com.simplebbs.dao;

import com.simplebbs.po.Comments;
import com.simplebbs.po.Posts;
import com.simplebbs.po.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface PersonDao {
    UserInfo FindUserInfobyid(@Param("user_id")int userId);
    Posts FindUserPosts(@Param("user_id")int userId);
    Comments FindUserComment(@Param("user_id")int userId);
}
