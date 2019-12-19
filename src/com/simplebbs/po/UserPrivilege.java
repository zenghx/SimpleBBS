package com.simplebbs.po;

public class UserPrivilege {
    private int user_id;
    private boolean able_post;
    private boolean able_comment;
    private boolean admin;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public boolean isAble_comment() {
        return able_comment;
    }

    public boolean isAble_post() {
        return able_post;
    }

    public void setAble_comment(boolean able_comment) {
        this.able_comment = able_comment;
    }

    public void setAble_post(boolean able_post) {
        this.able_post = able_post;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }
}
