package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.user.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
