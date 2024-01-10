package com.artdevs.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.ProgramingLanguage;
import com.artdevs.domain.entities.user.Skill;
import com.artdevs.dto.ProgramingLanguageDTO;

public class ProgramingLanguageMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ProgramingLanguageDTO convertToProgramingLanguageDTO(ProgramingLanguage programingLanguage) {
        ProgramingLanguageDTO programingLanguageDTO = modelMapper.map(programingLanguage, ProgramingLanguageDTO.class);
        programingLanguageDTO.setListDemand(getListDemand(programingLanguage));
        programingLanguageDTO.setListSkills(getListSkill(programingLanguage));
        return programingLanguageDTO;
    }

    public static ProgramingLanguage convertToProgramingLanguage(ProgramingLanguageDTO programingLanguageDTO) {
        ProgramingLanguage programingLanguage = modelMapper.map(programingLanguageDTO, ProgramingLanguage.class);
        return programingLanguage;
    }

    private static List<Demand> getListDemand(ProgramingLanguage language) {
        return language.getDemandLanguage().stream()
                .map(demand -> new Demand(demand.getId(), demand.getDescription(), demand.getDesiredTime(),
                        demand.getUser(), demand.getLanguage()))
                .collect(Collectors.toList());
    }

    private static List<Skill> getListSkill(ProgramingLanguage language) {
        return language.getSkillLanguage().stream()
                .map(skill -> new Skill(skill.getId(), skill.getDescription(), skill.getUser(), skill.getLanguage()))
                .collect(Collectors.toList());
    }
}
