package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.ProgramingLanguage;
import com.artdevs.dto.user.ProgramingLanguageDTO;

public class ProgramingLanguageMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ProgramingLanguageDTO convertToProgramingLanguageDTO(ProgramingLanguage programingLanguage) {
        ProgramingLanguageDTO programingLanguageDTO = modelMapper.map(programingLanguage, ProgramingLanguageDTO.class);
        return programingLanguageDTO;
    }

    public static ProgramingLanguage convertToProgramingLanguage(ProgramingLanguageDTO programingLanguageDTO) {
        ProgramingLanguage programingLanguage = modelMapper.map(programingLanguageDTO, ProgramingLanguage.class);
        return programingLanguage;
    }
}
