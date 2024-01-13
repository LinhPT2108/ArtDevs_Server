package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.Skill;
import com.artdevs.repositories.user.SkillRepository;
import com.artdevs.services.SkillService;

public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillRepository skillRepository;

    @Override
    public Skill findSkillById(Integer skillId) {
        Optional<Skill> skillOptional = skillRepository.findById(skillId);
        return skillOptional.orElse(null);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public void deleteSkill(Skill skill) {
        skillRepository.delete(skill);
    }

}
