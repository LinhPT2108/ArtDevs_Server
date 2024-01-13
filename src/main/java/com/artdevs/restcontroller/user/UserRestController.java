package com.artdevs.restcontroller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.UserDTO;
import com.artdevs.dto.UserRegisterDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.repositories.user.PrograminglanguageRepository;
import com.artdevs.repositories.user.SkillRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class UserRestController {
	@Autowired 
	UserRepository userRepository;
	@Autowired SkillRepository skillrep;
	
	@Autowired PrograminglanguageRepository programingrepositories;
	
	@PostMapping("/user")
	public ResponseEntity<User> postUser(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok(userRepository.save(UserMapper.UserDTOconvertToUser(userDTO)));
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<UserDTO>> getUser(){
		List<User> listUser =userRepository.findAll();
		List<UserDTO> listUserDTO = new ArrayList<>();
		for (User user : listUser) {
			listUserDTO.add(UserMapper.UserConvertToUserDTO(user));
		}
		return ResponseEntity.ok(listUserDTO);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> RegisterUser(@RequestBody UserRegisterDTO RegisterDTO){
		return ResponseEntity.ok(userRepository.save(UserMapper.RegisterDTOconvertToUser(RegisterDTO,programingrepositories,userRepository)));
	}
	
	@GetMapping("/register/{userid}")
	public ResponseEntity<UserRegisterDTO> RegisterUser(@PathVariable String userid){
		UserRegisterDTO register = UserMapper.UserDTOconvertToRegisterDTO(userRepository.findById(userid).get());
		return ResponseEntity.ok(register);
	}
	
	
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String userid){
		
		UserDTO userdto = UserMapper.UserConvertToUserDTO(userRepository.findById(userid).get());
		return ResponseEntity.ok(userdto);
	}
	
	}
