package com.artdevs.dto.post;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private int id;

    private String reportDetail;
    
    private String ReportPostId;

    private String ReportUserId;

    private String UserIdActionReport;

    private Date TimeReport;
    
}
