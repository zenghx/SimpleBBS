package com.simplebbs.po;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Posts {
    private long post_id;
    private int author;
    private String title;
    private String content;
    private boolean allow_comment;
    private int likes;
    private int dislikes;
    private Date post_time;
    private int section_id;
    private List<Comments> commentsList;

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public boolean isAllow_comment() {
        return allow_comment;
    }
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    public Date getPost_time() {
        return post_time;
    }

    public int getAuthor() {
        return author;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setAllow_comment(boolean allow_comment) {
        this.allow_comment = allow_comment;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }
}
