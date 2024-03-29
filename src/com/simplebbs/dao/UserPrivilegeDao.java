package com.simplebbs.dao;

import com.simplebbs.po.UserPrivilege;
import org.apache.ibatis.annotations.Param;

public interface UserPrivilegeDao {
    UserPrivilege FindUserPrivilege(@Param("user_id") int userId);

    Integer UpdateUserPrivilege(@Param("user_id") int userId, @Param("canpost") boolean able_post,
                                @Param("cancomment") boolean able_comment, @Param("admin") boolean admin);

    Integer AddUserPrivilege(@Param("user_id") int userId, @Param("canpost") boolean able_post,
                             @Param("cancomment") boolean able_comment, @Param("admin") boolean admin);
}
