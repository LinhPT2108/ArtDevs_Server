package com.artdevs.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.UserDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.UserService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class UserRestController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@PostMapping("/user")
	public ResponseEntity<User> postUser(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(userRepository.save(UserMapper.convertToUser(userDTO)));
	}

	@GetMapping("/user")
	public ResponseEntity<List<User>> getUser() {
		return ResponseEntity.ok(userRepository.findAll());
	}
}
