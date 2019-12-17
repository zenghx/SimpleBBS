package com.simplebbs.po;

import java.sql.Date;

public class UserInfo {
    private int user_id;   //用户ID
    private String pwd_hash;     //用户密码
    private String user_name;    //用户名
    private String avatar_url;    //用户头像
    private int gender;        //用户性别
    private Date birthday;        //用户生日

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
