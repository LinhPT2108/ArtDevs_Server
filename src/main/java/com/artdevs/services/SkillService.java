package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.user.Skill;

public interface SkillService {
    Skill findSkillById(Integer skillId);

    List<Skill> findAll();

    Skill saveSkill(Skill skill);

    Skill updateSkill(Skill skill);

    void deleteSkill(Skill skill);
}
