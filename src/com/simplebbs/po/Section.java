package com.simplebbs.po;

public class Section {
    private Integer section_id;
    private String section_name;
    private Boolean allow_new_post;

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public Boolean getAllow_new_post() {
        return allow_new_post;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setAllow_new_post(Boolean allow_new_post) {
        this.allow_new_post = allow_new_post;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }
}
