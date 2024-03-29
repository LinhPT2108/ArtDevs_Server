package com.artdevs.restcontroller.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.CustomDTO.UserGetRelationDTO;
import com.artdevs.dto.message.RelationShipDTO;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.mapper.message.RelationShipMapper;
import com.artdevs.mapper.post.PostMapper;
import com.artdevs.repositories.message.RelationshipRepository;
import com.artdevs.services.RelationshipService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;
import com.artdevs.utils.CustomContructor;

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
	@GetMapping("/get-listfriend-suitable")
	public ResponseEntity<?> getListSuitableFriend(@RequestParam("page") Optional<Integer> p){
		
		//Lấy UserLogin từ Token
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User userlogin = userservice.findByEmail(auth.getName());
		List<User> UserSuitable = userservice.findSuitableFriend();
		
		UserSuitable.remove(userlogin);
		List<User> resultTemp = UserSuitable.stream()
                .distinct()
                .collect(Collectors.toList());
		Pageable pageable = PageRequest.of(p.orElse(0), 12);
		int start = (int) pageable.getOffset();
		int ListSize = resultTemp.size();
		int end = Math.min((start + pageable.getPageSize()), resultTemp.size());
//		System.out.println("Check End"  + end);
//		System.out.println("Check start"  + start);
//		System.out.println("Check ListSize"  + ListSize);
		 
	try {
		if( end > ListSize) {
			int EndTemp = resultTemp.size();
			
			Page<User> userPage = new PageImpl<>(resultTemp.subList(start, EndTemp), pageable, resultTemp.size());
			return ResponseEntity.ok( userPage.stream().map(t -> UserMapper.UserConvertToUserGetDTO(t)).collect(Collectors.toList()));
		}else {
	
			
			Page<User> userPage = new PageImpl<>(resultTemp.subList(start, end), pageable, resultTemp.size());
			return ResponseEntity.ok( userPage.stream().map(t -> UserMapper.UserConvertToUserGetDTO(t)).collect(Collectors.toList()));
		}
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return ResponseEntity.badRequest().build();
	}
		
	
	}
	
	@GetMapping("/get-request-friend")
	public ResponseEntity<?> Getrequestfriend(@RequestParam("page") Optional<Integer> p) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("check Usetloger" + auth.getName());
		String LoggerUserID = userservice.findByEmail(auth.getName()).getUserId();
		
		List<RelationShip> listrelation ;
		
		
		
		try {
			listrelation = relationservice.findRelationshipByUserIdAndStatus(LoggerUserID);
			Pageable pageable = PageRequest.of(p.orElse(0), 12);
			int start = (int) pageable.getOffset();
			int ListSize = listrelation.size();
			int end = Math.min((start + pageable.getPageSize()), listrelation.size());
		
			List<RelationShip> result = listrelation.subList(start, end);
			Page<RelationShip> FriendPage = new PageImpl<>(result, pageable, listrelation.size());
		
			return ResponseEntity
					.ok(FriendPage.stream().map(t -> new RelationShipDTO(t.getId(), t.getStatus(), t.getTimeRelation(),
							RelationShipMapper.setUserGetRelation(t.getActionUser()), t.getUserOneId().getUserId(), t.getUserTwoId().getUserId())).collect(Collectors.toList()));
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
	@PostMapping("/remove-user-of-listfriend-suitable/{UserID}")
	public ResponseEntity<?> removeUserOfList(@PathVariable("UserID") String ToUserID){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String LoggerUserID = userservice.findByEmail(auth.getName()).getUserId();
		
		try {
			return ResponseEntity.ok(relationservice.removeUserOfListSuitable(LoggerUserID, ToUserID,9));
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
	public ResponseEntity<?> allfriend(){
//		System.out.println(relationrepository.findRelationshipByUserIdAndStatusAndOnline("Aa127", 1, true));
		
		
		List<UserGetRelationDTO> result = relationservice.getAllFriend()
		        .stream()
		        .map(user -> UserMapper.UserConvertToUserGetDTO(user))
		        .collect(Collectors.toList());
		System.out.println("check Result" + result);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/get-list-mentor-match")
	public ResponseEntity<?> allmentormatch(){
//		System.out.println(relationrepository.findRelationshipByUserIdAndStatusAndOnline("Aa127", 1, true));
		
		
		List<UserGetRelationDTO> result = relationservice.getAllMentor()
		        .stream()
		        .map(user -> UserMapper.UserConvertToUserGetDTO(user))
		        .collect(Collectors.toList());
		System.out.println("check Result" + result);
		return ResponseEntity.ok(result);
	}
}
