package com.artdevs.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.ProgramingLanguage;
import com.artdevs.domain.entities.user.Skill;
import com.artdevs.dto.user.ProgramingLanguageDTO;


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

    private static List<DemandDTO> getListDemand(ProgramingLanguage language) {
        List<DemandDTO> demandDTO = new ArrayList<>();
        List<Demand> demands = language.getDemandLanguage();
        for (Demand demand : demands) {
            demandDTO.add(DemandMapper.convertToDemandDTO(demand));
        }

        return demandDTO;
    }

    private static List<SkillDTO> getListSkill(ProgramingLanguage language) {
        List<SkillDTO> skillDTO = new ArrayList<>();
        List<Skill> skills = language.getSkillLanguage();
        for (Skill skill : skills) {
            skillDTO.add(SkillMapper.convertToSkillDTO(skill));
        }

        return skillDTO;
    }
}
