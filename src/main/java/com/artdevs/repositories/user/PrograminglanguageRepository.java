package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.ProgramingLanguage;
import java.util.List;

@Repository
public interface PrograminglanguageRepository extends JpaRepository<ProgramingLanguage, Integer> {
	@Query("SELECT c FROM ProgramingLanguage c WHERE c.LanguageName = :languageName")
	ProgramingLanguage findByLanguageName(String languageName);
}
