package com.artdevs.restcontroller;

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
import com.artdevs.services.impl.user.ProgramingLanguageServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class ProgramingLanguageRestController {
    @Autowired
    ProgramingLanguageServiceImpl programingLanguageServiceImpl;

    @PostMapping("/programingLanguage")
    public ResponseEntity<ProgramingLanguage> postProgramingLanguage(
            @RequestBody ProgramingLanguageDTO programingLanguageDTO) {
        return ResponseEntity
                .ok(programingLanguageServiceImpl
                        .saveProgramingLanguage(ProgramingLanguageMapper
                                .convertToProgramingLanguage(programingLanguageDTO)));
    }

    @GetMapping("/programingLanguage")
    public ResponseEntity<List<ProgramingLanguage>> getProgramingLanguage() {
        return ResponseEntity.ok(programingLanguageServiceImpl.findAll());
    }
}
