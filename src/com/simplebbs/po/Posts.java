package com.simplebbs.po;


import java.util.Date;

public class Posts {
    private long post_id;
    private int author;
    private String title;
    private String content;
    private boolean allow_comment;
    private int likes;
    private int dislikes;
    private Date post_time;
    private String section;

    public boolean isAllow_comment() {
        return allow_comment;
    }

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

    public String getSection() {
        return section;
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

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
