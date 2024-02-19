package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Report;
import com.artdevs.dto.post.ReportDTO;
import com.artdevs.mapper.post.ReportMapper;
import com.artdevs.repositories.post.ReportRepository;
import com.artdevs.services.PostService;
import com.artdevs.services.ReportService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class ReportRestController {
    @Autowired
    ReportService reportService;

    @Autowired
    ReportRepository reportRepository;

    @Autowired UserService userserivce;
    
    @Autowired PostService postservice;
    
    @PostMapping("/report")
    public ResponseEntity<Report> postReport(@RequestBody ReportDTO reportDTO) {
        return ResponseEntity.ok(reportService.saveReport(ReportMapper.convertToReport(reportDTO,userserivce,postservice)));
    }

    @GetMapping("/report")
    public ResponseEntity<List<ReportDTO>> getReport() {
        List<ReportDTO> listReportDTO = new ArrayList<>();
        List<Report> listReport =  reportService.findAll();
        for (Report report : listReport) {
            listReportDTO.add(ReportMapper.convertToReprotDTO(report));
        }
        return ResponseEntity.ok(listReportDTO);
    }
    
    @GetMapping("/report/{postID}")
    public ResponseEntity<List<ReportDTO>> getReportByID(@PathVariable("postID") String postID) {
        List<ReportDTO> listReportDTO = new ArrayList<>();
        List<Report> listReport =  reportService.FindByPost(postservice.findPostById(postID));
        for (Report report : listReport) {
            listReportDTO.add(ReportMapper.convertToReprotDTO(report));
        }
        return ResponseEntity.ok(listReportDTO);
    }
}
