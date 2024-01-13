package com.artdevs.repositories.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.User;



@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
	@Query("SELECT c FROM Skill c WHERE c.language.LanguageName = :languageName")
	Skill findByLanguageName(String languageName);

	List<Skill> findByUser(User user);
}
