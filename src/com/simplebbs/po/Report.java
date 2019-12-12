package com.simplebbs.po;

import java.util.Date;

public class Report {
    private long record_id;
    private long post_id;
    private long comment_id;
    private int reporter;
    private String report_reason;
    private Date report_time;

    public void setRecord_id(long record_id) {
        this.record_id = record_id;
    }

    public long getRecord_id() {
        return record_id;
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public long getComment_id() {
        return comment_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public int getReporter() {
        return reporter;
    }

    public String getReport_reason() {
        return report_reason;
    }

    public void setReport_reason(String report_reason) {
        this.report_reason = report_reason;
    }

    public void setReporter(int reporter) {
        this.reporter = reporter;
    }
}
