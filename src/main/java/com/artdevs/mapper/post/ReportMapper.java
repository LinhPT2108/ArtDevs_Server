package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Report;
import com.artdevs.dto.post.ReportDTO;

public class ReportMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ReportDTO convertToReprotDTO(Report report) {
        ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);
        return reportDTO;
    }

    public static Report convertToReport(ReportDTO reportDTO) {
        Report report = modelMapper.map(reportDTO, Report.class);
        return report;
    }
}
