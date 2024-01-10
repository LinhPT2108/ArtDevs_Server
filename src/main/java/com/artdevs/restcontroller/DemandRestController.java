package com.artdevs.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.dto.user.DemandDTO;
import com.artdevs.mapper.DemandMapper;
import com.artdevs.repositories.user.DemandRepository;
import com.artdevs.services.impl.user.DemandServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class DemandRestController {
    @Autowired
    DemandServiceImpl demandServiceImpl;

    @PostMapping("/demand")
    public ResponseEntity<Demand> postDemand(@RequestBody DemandDTO demandDTO) {
        return ResponseEntity.ok(demandServiceImpl.saveDemand(DemandMapper.convertToDemand(demandDTO)));
    }

    @GetMapping("/demand")
    public ResponseEntity<List<Demand>> getDemand() {
        return ResponseEntity.ok(demandServiceImpl.findAll());
    }
}
