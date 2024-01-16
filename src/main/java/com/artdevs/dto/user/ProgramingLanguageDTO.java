package com.artdevs.dto.user;

import java.util.List;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramingLanguageDTO {

    private int id;

    @Nationalized
    private String LanguageName;

    private List<DemandDTO> listDemand;

    private List<SkillDTO> listSkills;
}
