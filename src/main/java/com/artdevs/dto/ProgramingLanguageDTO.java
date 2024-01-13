package com.artdevs.dto;

import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.Skill;

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

    private List<Demand> listDemand;

    private List<Skill> listSkills;
}
