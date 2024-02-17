package com.artdevs.restcontroller.message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.dto.message.RelationShipDTO;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.mapper.message.RelationShipMapper;
import com.artdevs.repositories.message.RelationshipRepository;
import com.artdevs.services.RelationshipService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class RestRealtionShip {

	@Autowired
	RelationshipService relationservice;

	@Autowired
	UserService userservice;


	@Autowired
	RelationshipRepository relationrepository;

//	@GetMapping("/relationship")
//	public ResponseEntity<List<RelationShipDTO>> getrelationship(){
//		List<RelationShip> listrelationship = relationrepository.findAll();
//		List<RelationShipDTO> DTOList = new ArrayList<>();
//		for (RelationShip relationship : listrelationship) {
//			DTOList.add(RelationShipMapper.convertToRelationShipDTO(relationship));
//		}
//		return ResponseEntity.ok(DTOList);
//
//	}
//
//	@PostMapping("/relationship")
//	public ResponseEntity<RelationShip> setRelationship(@RequestBody RelationShipDTO relationshipdto) {
//		return ResponseEntity.ok(RelationShipMapper.convertToRelationShip(relationshipdto, userservice));
//	}

	@GetMapping("/get-request-friend")
	public ResponseEntity<List<RelationShipDTO>> Getrequestfriend() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String LoggerUserID = userservice.findByEmail(auth.getName()).getUserId();
		List<RelationShip> listrelation;
		try {
			listrelation = relationservice.findRelationshipByUserIdAndStatus(LoggerUserID);
			return ResponseEntity
					.ok(listrelation.stream().map(t -> new RelationShipDTO(t.getId(), t.getStatus(), t.getTimeRelation(),
							t.getActionUser().getUserId(), t.getUserOneId().getUserId(), t.getUserTwoId().getUserId())).collect(Collectors.toList()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}

		
	}
	
	@PostMapping("/accept-friend/{UserID}")
	public ResponseEntity<Boolean> acceptrequest(@PathVariable("UserID") String ToUserID){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String LoggerUserID = userservice.findByEmail(auth.getName()).getUserId();
		try {
			return ResponseEntity.ok(relationservice.acceptFriend(LoggerUserID,ToUserID ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		
		}
	}
	@PostMapping("/send-request-friend/{UserID}")
	public ResponseEntity<?> sendfiendrequest(@PathVariable("UserID") String ToUserID){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String LoggerUserID = userservice.findByEmail(auth.getName()).getUserId();
		
		try {
			return ResponseEntity.ok(relationservice.createRequestForAddingFriend(LoggerUserID, ToUserID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/remove-friend/{UserID}")
	public ResponseEntity<?> removefiendrequest(@PathVariable("UserID") String ToUserID){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String LoggerUserID = userservice.findByEmail(auth.getName()).getUserId();
		
		try {
			return ResponseEntity.ok(relationservice.removeFriend(LoggerUserID, ToUserID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/cancel-request-friend/{UserID}")
	public ResponseEntity<?> cancelfiendrequest(@PathVariable("UserID") String ToUserID){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String LoggerUserID = userservice.findByEmail(auth.getName()).getUserId();
		
		try {
			return ResponseEntity.ok(relationservice.cancelFriendshipRequest(LoggerUserID, ToUserID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/get-friend-online")
	public ResponseEntity<List<UserDTO>> friendisonline(){
//		System.out.println(relationrepository.findRelationshipByUserIdAndStatusAndOnline("Aa127", 1, true));
		List<UserDTO> result = relationservice.getFriendOnline()
		        .stream()
		        .map(user -> UserMapper.UserConvertToUserDTO(user))
		        .collect(Collectors.toList());
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/get-list-friend")
	public ResponseEntity<List<UserDTO>> allfriend(){
//		System.out.println(relationrepository.findRelationshipByUserIdAndStatusAndOnline("Aa127", 1, true));
		List<UserDTO> result = relationservice.getAllFriend()
		        .stream()
		        .map(user -> UserMapper.UserConvertToUserDTO(user))
		        .collect(Collectors.toList());
		return ResponseEntity.ok(result);
	}
}
