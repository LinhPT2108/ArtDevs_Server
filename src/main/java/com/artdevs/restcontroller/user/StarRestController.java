package com.artdevs.restcontroller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Star;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.user.StarDTO;
import com.artdevs.mapper.StarMapper;
import com.artdevs.services.StarService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(Path.path_api)
public class StarRestController {
	@Autowired
	StarService starService;
	@Autowired 
	UserService userService;
	
	@PostMapping("/star")
	public ResponseEntity<StarDTO> getMethodName(@RequestBody StarDTO starDto) {
		Star star = starService.createStar(StarMapper.convertToStar(starDto));
		return ResponseEntity.ok(StarMapper.convertToStarDTO(star));
	}
	
	@GetMapping("/star-of-student")
	public ResponseEntity<StarDTO> getMethodName(@RequestParam("mentorId") String mentorId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userLoggedEmail = authentication.getName();
		User mentor = userService.findUserById(mentorId);
		System.out.println(userLoggedEmail);
		User userLogged = userService.findByEmail(userLoggedEmail);
		Star star = starService.findStarByUserLoggedAndMentor(userLogged, mentor);
		return ResponseEntity.ok(StarMapper.convertToStarDTO(star));
	}
	
	@PutMapping("/update-star")
	public ResponseEntity<StarDTO> putMethodName(@RequestBody StarDTO starDTO) {
		//TODO: process PUT request
		Star starUpdate = starService.updateStar(StarMapper.convertToStar(starDTO));
		return ResponseEntity.ok(StarMapper.convertToStarDTO(starUpdate));
	}
	
	@PostMapping("/delete-star")
	public ResponseEntity<Boolean> postMethodName(@RequestParam("starId") String starId) {
		//TODO: process POST request
		try {
			Star star = starService.findById(Integer.parseInt(starId));
			starService.deleteStar(star);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return ResponseEntity.ok(false);
		}
	}
	
}
