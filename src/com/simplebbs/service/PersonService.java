package com.simplebbs.service;

import com.simplebbs.po.UserInfo;
import com.simplebbs.po.Comments;
import com.simplebbs.po.Posts;

public interface PersonService {
    UserInfo FindUserInfobyid(int userId);
    Posts FindUserPosts(int userId);
    Comments FindUserComment(int userId);
}
