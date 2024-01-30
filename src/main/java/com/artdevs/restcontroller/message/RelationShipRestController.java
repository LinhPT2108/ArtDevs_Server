package com.artdevs.restcontroller.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.dto.message.RelationShipDTO;
import com.artdevs.mapper.message.RelationShipMapper;
import com.artdevs.repositories.message.RelationshipRepository;
import com.artdevs.services.RelationshipService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)

public class RelationShipRestController {

    @Autowired
    RelationshipService relationshipService;

    @Autowired
    RelationshipRepository relationshipRepository;

    @GetMapping("/relationship")
    public ResponseEntity<List<RelationShipDTO>> getRelationShip() {
        List<RelationShipDTO> listRelationShipDTO = new ArrayList<>();
        List<RelationShip> listRelationShips = relationshipRepository.findAll();
        for (RelationShip relationShip : listRelationShips) {
            listRelationShipDTO.add(RelationShipMapper.convertToRelationShipDTO(relationShip));
        }
        return ResponseEntity.ok(listRelationShipDTO);
    }
}
