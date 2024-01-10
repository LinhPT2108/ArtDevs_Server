package com.artdevs.dto;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DemandDTO {

    private int id;

    @Nationalized
    private String description;

    private String desiredTime;

    private String username;

    private String programingLanguage;

}
