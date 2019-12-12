package com.simplebbs.po;

public class Report {
    private long post_id;
    private long comment_id;
    private int reporter;
    private String report_reason;

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
