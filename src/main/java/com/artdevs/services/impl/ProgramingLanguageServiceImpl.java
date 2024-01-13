package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.ProgramingLanguage;
import com.artdevs.repositories.user.PrograminglanguageRepository;
import com.artdevs.services.ProgramingLanguageService;

public class ProgramingLanguageServiceImpl implements ProgramingLanguageService {

    @Autowired
    PrograminglanguageRepository programinglanguageRepository;

    @Override
    public ProgramingLanguage findRoleById(Integer programingLanguageId) {
        Optional<ProgramingLanguage> programingLanguageOptional = programinglanguageRepository
                .findById(programingLanguageId);
        return programingLanguageOptional.orElse(null);
    }

    @Override
    public List<ProgramingLanguage> findAll() {
        return programinglanguageRepository.findAll();
    }

    @Override
    public ProgramingLanguage saveProgramingLanguage(ProgramingLanguage programingLanguage) {
        return programinglanguageRepository.save(programingLanguage);
    }

    @Override
    public ProgramingLanguage updateProgramingLanguage(ProgramingLanguage programingLanguage) {
        return programinglanguageRepository.save(programingLanguage);
    }

    @Override
    public void deleteProgramingLanguage(ProgramingLanguage programingLanguage) {
        programinglanguageRepository.delete(programingLanguage);
    }

}
