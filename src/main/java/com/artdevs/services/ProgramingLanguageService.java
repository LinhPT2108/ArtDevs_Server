package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.user.ProgramingLanguage;

public interface ProgramingLanguageService {
    ProgramingLanguage findRoleById(Integer programingLanguageId);

    List<ProgramingLanguage> findAll();

    ProgramingLanguage saveProgramingLanguage(ProgramingLanguage programingLanguage);

    ProgramingLanguage updateProgramingLanguage(ProgramingLanguage programingLanguage);

    void deleteProgramingLanguage(ProgramingLanguage programingLanguage);
}
