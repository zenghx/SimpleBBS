package com.simplebbs.po;

import java.sql.Date;

public class UserInfo {
    private int user_id;
    private String pwd_hash;
    private String user_name;
    private String avatar_url;
    private int gender;
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getPwd_hash() {
        return pwd_hash;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setPwd_hash(String pwd_hash) {
        this.pwd_hash = pwd_hash;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
