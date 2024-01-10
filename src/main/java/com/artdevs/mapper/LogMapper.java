package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Log;
import com.artdevs.dto.user.LogDTO;

public class LogMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static LogDTO convertToLogDTO(Log log) {
        LogDTO logDTO = modelMapper.map(log, LogDTO.class);
        return logDTO;
    }

    public static Log convertToLog(LogDTO logDTO) {
        Log log = modelMapper.map(logDTO, Log.class);
        return log;
    }
}
