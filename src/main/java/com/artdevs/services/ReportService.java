package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Report;

public interface ReportService {
    Report findReportById(Integer reportId);
    
    List<Report> FindByPost(Post post);

    List<Report> findAll();

    Report saveReport(Report report);

    Report updateReport(Report report);

    void deleteReport(Report reportd);
}
