package com.artdevs.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.ProgramingLanguage;
import com.artdevs.dto.user.ProgramingLanguageDTO;
import com.artdevs.mapper.ProgramingLanguageMapper;
import com.artdevs.repositories.user.PrograminglanguageRepository;
import com.artdevs.services.ProgramingLanguageService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class ProgramingLanguageRestController {
    @Autowired
    ProgramingLanguageService programingLanguageService;

    @Autowired
    PrograminglanguageRepository programingRepositories;

    @PostMapping("/programingLanguage")
    public ResponseEntity<ProgramingLanguage> postProgramingLanguage(
            @RequestBody ProgramingLanguageDTO programingLanguageDTO) {
        return ResponseEntity
                .ok(programingLanguageService
                        .saveProgramingLanguage(ProgramingLanguageMapper
                                .convertToProgramingLanguage(programingLanguageDTO)));
    }

    @GetMapping("/programingLanguage")
    public ResponseEntity<List<ProgramingLanguageDTO>> getProgramingLanguage() {
        List<ProgramingLanguageDTO> listProgramingLanguageDTO = new ArrayList<>();
        List<ProgramingLanguage> listProgramingLanguages = programingRepositories.findAll();
        for (ProgramingLanguage language : listProgramingLanguages) {
            listProgramingLanguageDTO.add(ProgramingLanguageMapper.convertToProgramingLanguageDTO(language));
        }
        return ResponseEntity.ok(listProgramingLanguageDTO);
    }
}
