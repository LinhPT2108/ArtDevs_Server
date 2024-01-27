package com.artdevs.restcontroller.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.dto.message.RelationShipDTO;
import com.artdevs.mapper.message.RelationShipMapper;
import com.artdevs.services.RelationshipService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class RestRealtionShip {
		
	@Autowired RelationshipService relationservice;
	
	@Autowired UserService userservice;
	
//	@GetMapping("/relationship")
//	public ResponseEntity<List<RelationShipDTO>> getrelationship(){
////		List<RelationShip> listrelationship = relationservice.findAll();
//		List<RelationShipDTO> DTOList = new ArrayList<>();
//		for (RelationShip relationship : listrelationship) {
//			DTOList.add(RelationShipMapper.convertToRelationShipDTO(relationship));
//		}
//		return ResponseEntity.ok(DTOList);
//	}
	
	@PostMapping("/relationship")
	public ResponseEntity<RelationShip> setRelationship(@RequestBody RelationShipDTO relationshipdto){
		return ResponseEntity.ok(RelationShipMapper.convertToRelationShip(relationshipdto, userservice));
	}
}
