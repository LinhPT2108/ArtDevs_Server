package com.artdevs.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.TransitionInfo;
import com.artdevs.dto.TransitionInfoDTO;
import com.artdevs.mapper.TransitionInfoMapper;
import com.artdevs.repositories.user.TransitioninfoRepository;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class TransitionInfoRestController {
    @Autowired
    TransitioninfoRepository transitioninfoRepository;

    @PostMapping("/transitionInfo")
    public ResponseEntity<TransitionInfo> postTransitionInfo(@RequestBody TransitionInfoDTO transitionInfoDTO) {
        return ResponseEntity
                .ok(transitioninfoRepository
                        .save(TransitionInfoMapper
                                .convertToTransitionInfo(transitionInfoDTO)));
    }

    @GetMapping("/transitionInfo")
    public ResponseEntity<List<TransitionInfo>> getTransitionInfo() {
        return ResponseEntity.ok(transitioninfoRepository.findAll());
    }
}
