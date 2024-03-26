package com.artdevs.mapper.post;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Report;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.post.ReportDTO;
import com.artdevs.services.PostService;
import com.artdevs.services.UserService;

public class ReportMapper {
	

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ReportDTO convertToReprotDTO(Report report) {
        ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);
       reportDTO.setReportPostId(report.getPostReportId().getPostId());
       reportDTO.setReportUserId(report.getPostReportId().getUser().getUserId());
       reportDTO.setUserIdActionReport(report.getUserReportId().getUserId());
       reportDTO.setTimeReport(report.getTimeCreate());
        return reportDTO;
    }

//    public static Report convertToReport(ReportDTO reportDTO,UserService userservice,PostService postservice) {
//        Report report = modelMapper.map(reportDTO, Report.class);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userservice.findByEmail(auth.getName());
//        Post post = postservice.findPostById(reportDTO.getPostId());
//        report.setUserReportId(user);
//        report.setPostReportId(post);
//        report.setTimeCreate(new Date());
//        return report;
//    }
}
