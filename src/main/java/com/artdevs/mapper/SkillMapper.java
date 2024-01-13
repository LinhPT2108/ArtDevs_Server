package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Skill;
import com.artdevs.dto.user.SkillDTO;

public class SkillMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static SkillDTO convertToSkillDTO(Skill skill) {
        SkillDTO skillDTO = modelMapper.map(skill, SkillDTO.class);
        skillDTO.setLanguageName(skill.getLanguage().getLanguageName());
        skillDTO.setUserId(skill.getUser().getUserId());
        return skillDTO;
    }

    public static Skill convertToSkill(SkillDTO skillDTO) {
        Skill skill = modelMapper.map(skillDTO, Skill.class);
        return skill;
    }
}
