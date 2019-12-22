package com.simplebbs.service.impl;

import com.simplebbs.dao.ReportDao;
import com.simplebbs.dao.UserPrivilegeDao;
import com.simplebbs.po.Report;
import com.simplebbs.po.UserPrivilege;
import com.simplebbs.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{
    private ReportDao reportDao;
    private UserPrivilegeDao userPrivilegeDao;
    @Autowired
    void setReportDao(ReportDao reportDao){this.reportDao = reportDao;}
    @Autowired
    void setUserPrivilegeDao(UserPrivilegeDao userPrivilegeDao){this.userPrivilegeDao = userPrivilegeDao;}

    @Override
    public Integer AddCommentReport(long post_id, long comment_id, int reporter_id, String report_reason, Date report_time){
        if(reporter_id<=0)
            return -1;
        if(post_id<=0)
            return -1;
        if(comment_id<=0)
            return -1;
        if(report_reason.isBlank())
            return -1;
        Report report = new Report();
        report.setPost_id(post_id);
        report.setComment_id(comment_id);
        report.setReporter(reporter_id);
        report.setReport_reason(report_reason);
        report.setReport_time(report_time);
        if(reportDao.AddCommentReport(post_id, comment_id, reporter_id, report_reason, report_time)<0)
            return -1;
        else return report.getRecord_id();
    }

    @Override
    public Integer AddPostReport(long post_id,int reporter_id, String report_reason, Date report_time){
        if(reporter_id<=0)
            return -1;
        if(post_id<=0)
            return -1;
        if(report_reason.isBlank())
            return -1;
        Report report = new Report();
        report.setPost_id(post_id);
        report.setReporter(reporter_id);
        report.setComment_id(0);
        report.setReport_reason(report_reason);
        report.setReport_time(report_time);
        if(reportDao.AddPostReport(post_id,reporter_id, report_reason, report_time)<0)
            return -1;
        else return report.getRecord_id();
    }

    @Override
    @Transactional
    public Integer HandleReport(int record_id){
        if(reportDao.FindReportbyid(record_id) == null)
            return -1;
        else{
            reportDao.HandleReport(record_id);
            return 1;
        }
    }

    @Override
    @Transactional
    public Report FindReportbyid(int record_id){
        if(record_id<0){return null;}
        if(reportDao.FindReportbyid(record_id) == null){return null;}
        return reportDao.FindReportbyid(record_id);
    }
}
