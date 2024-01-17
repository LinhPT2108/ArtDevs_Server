package com.artdevs.dto.user;

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

    private String description;

    private String desiredTime;

    private String username;

    private String programingLanguage;

}
