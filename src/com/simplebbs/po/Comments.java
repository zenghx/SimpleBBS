package com.simplebbs.po;

import java.util.Date;

public class Comments {
    private long comment_id;
    private long post_id;
    private int user_id;
    private String comment;
    private int likes;
    private int dislikes;
    private Date comment_time;

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }
}
