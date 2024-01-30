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

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.dto.user.DemandDTO;
import com.artdevs.mapper.DemandMapper;
import com.artdevs.repositories.user.DemandRepository;
import com.artdevs.services.DemandService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class DemandRestController {
    @Autowired
    DemandService demandService;

    @Autowired
    DemandRepository demandRepository;

    @PostMapping("/demand")
    public ResponseEntity<Demand> postDemand(@RequestBody DemandDTO demandDTO) {
        return ResponseEntity.ok(demandService.saveDemand(DemandMapper.convertToDemand(demandDTO)));
    }

    @GetMapping("/demand")
    public ResponseEntity<List<DemandDTO>> getDemand() {
        List<DemandDTO> listDemandDTO = new ArrayList<>();
        List<Demand> listDemand = demandRepository.findAll();
        for (Demand demand : listDemand) {
            listDemandDTO.add(DemandMapper.convertToDemandDTO(demand));
        }
        return ResponseEntity.ok(listDemandDTO);
    }
}
