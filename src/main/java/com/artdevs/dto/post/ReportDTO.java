package com.artdevs.dto.post;

import org.hibernate.annotations.Nationalized;

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

    @Nationalized
    private String reportDetail;

    private int count;

    private String username;

    private String postId;
}
