package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.post.Report;
import com.artdevs.repositories.post.ReportRepository;
import com.artdevs.services.ReportService;

public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public Report findReportById(Integer reportId) {
        Optional<Report> reportOptional = reportRepository.findById(reportId);
        return reportOptional.orElse(null);
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void deleteReport(Report reportd) {
        reportRepository.delete(reportd);
    }

}
