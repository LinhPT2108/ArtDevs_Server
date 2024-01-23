package com.artdevs.restcontroller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.TransitionInfo;
import com.artdevs.dto.transition.TransitionInfoDTO;
import com.artdevs.mapper.TransitionInfoMapper;
import com.artdevs.repositories.user.TransitioninfoRepository;
import com.artdevs.services.TransitionInfoService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class TransitionInfoRestController {
    @Autowired
    TransitionInfoService transitionInfoService;

    @Autowired
    TransitioninfoRepository transitioninfoRepository;

    @PostMapping("/transitionInfo")
    public ResponseEntity<TransitionInfo> postTransitionInfo(@RequestBody TransitionInfoDTO transitionInfoDTO) {
        return ResponseEntity
                .ok(transitionInfoService
                        .saveTransitionInfo(TransitionInfoMapper
                                .convertToTransitionInfo(transitionInfoDTO)));
    }

    @GetMapping("/transitionInfo")
    public ResponseEntity<List<TransitionInfoDTO>> getTransitionInfo() {
        List<TransitionInfoDTO> listTransitionInfoDTO = new ArrayList<>();
        List<TransitionInfo> listTransitionInfo = transitioninfoRepository.findAll();
        for (TransitionInfo transitionInfo : listTransitionInfo) {
            listTransitionInfoDTO.add(TransitionInfoMapper.convertToTransitionInfoDTO(transitionInfo));
        }
        return ResponseEntity.ok(listTransitionInfoDTO);
    }
}
