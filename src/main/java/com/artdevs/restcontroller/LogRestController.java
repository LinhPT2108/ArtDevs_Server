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

import com.artdevs.domain.entities.user.Log;
import com.artdevs.dto.user.LogDTO;
import com.artdevs.mapper.LogMapper;
import com.artdevs.repositories.user.LogRepository;
import com.artdevs.services.impl.user.LogServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class LogRestController {
    @Autowired
    LogServiceImpl logServiceImpl;

    @Autowired
    LogRepository LogRepository;

    @PostMapping("/log")
    public ResponseEntity<Log> postLog(@RequestBody LogDTO logDTO) {
        return ResponseEntity.ok(logServiceImpl.saveLog(LogMapper.convertToLog(logDTO)));
    }

    @GetMapping("/log")
    public ResponseEntity<List<LogDTO>> getLog() {
        List<LogDTO> listLogDTO = new ArrayList<>();
        List<Log> listLog = LogRepository.findAll();
        for (Log log : listLog) {
            listLogDTO.add(LogMapper.convertToLogDTO(log));
        }
        return ResponseEntity.ok(listLogDTO);
    }
}
