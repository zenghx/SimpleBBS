package com.simplebbs.service;

import com.simplebbs.po.Report;
import com.simplebbs.po.UserPrivilege;

import java.util.Date;
import java.util.List;

public interface ReportService {
    Integer AddCommentReport(long post_id, long comment_id, int reporter_id,
                             String report_reason, Date report_time);
    Integer AddPostReport(long post_id,int reporter_id,String report_reason,
                          Date report_time);
    Integer HandleReport(int record_id);
    Report FindReportbyid(int record_id);
}
