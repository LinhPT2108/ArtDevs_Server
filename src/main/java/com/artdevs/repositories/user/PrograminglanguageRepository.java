package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.ProgramingLanguage;
@Repository
public interface PrograminglanguageRepository extends JpaRepository<ProgramingLanguage, Integer> {

}
