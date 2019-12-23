package com.simplebbs.dao;

import com.simplebbs.po.Report;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ReportDao {
    Integer AddPostReport(@Param("post_id")long post_id, @Param("reporter_id")int reporter_id,
                          @Param("report_reason")String report_reason, @Param("report_time") Date report_time);
    Integer AddCommentReport(@Param("post_id")long post_id, @Param("comment_id")long comment_id,
                          @Param("reporter_id")int reporter_id, @Param("report_reason")String report_reason,
                          @Param("report_time") Date report_time);
    Integer HandleReport(@Param("record_id")int record_id);
    Report FindReportbyid(@Param("record_id")int record_id);
}
