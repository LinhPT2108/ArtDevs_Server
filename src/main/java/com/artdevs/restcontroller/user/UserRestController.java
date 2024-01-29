package com.artdevs.restcontroller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.UserRegisterDTO;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.repositories.user.DemandRepository;
import com.artdevs.repositories.user.PrograminglanguageRepository;
import com.artdevs.repositories.user.SkillRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.UserService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class UserRestController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired UserService userservice;
	@Autowired
	SkillRepository skillrep;

	@Autowired
	DemandRepository demandrepositories;

	@Autowired
	PrograminglanguageRepository programingrepositories;

	@PostMapping("/user")
	public ResponseEntity<User> postUser(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(userRepository.save(UserMapper.UserDTOconvertToUser(userDTO)));
	}

	@GetMapping("/user")
	public ResponseEntity<List<UserDTO>> getUser() {
		List<User> listUser = userRepository.findAll().stream().filter(t -> t.getRole().getId() == 3).toList();
		List<UserDTO> listUserDTO = new ArrayList<>();
		for (User user : listUser) {
			listUserDTO.add(UserMapper.UserConvertToUserDTO(user));
		}
		return ResponseEntity.ok(listUserDTO);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> RegisterUser(@RequestBody UserRegisterDTO RegisterDTO) {
		User user = userRepository.save(UserMapper.RegisterDTOconvertToUser(RegisterDTO));
		for (String skillname : RegisterDTO.getListSkillOfUser()) {
			Skill skill = new Skill();
			skill.setUser(user);
			skill.setLanguage(programingrepositories.findByLanguageName(skillname));
			skillrep.save(skill);
		}
		for (String demandname : RegisterDTO.getListDemandOfUser()) {
			Demand demand = new Demand();
			demand.setUser(user);
			demand.setLanguage(programingrepositories.findByLanguageName(demandname));
			demandrepositories.save(demand);
		}
		// System.out.println(demandrepositories.findByUser(user));
		// user.setUserSkill(skillrep.findByUser(user));
		UserDTO  userdto = UserMapper.UserRegisterConvertToUserDTO(RegisterDTO);
		return ResponseEntity.ok(userdto);
	}

	@GetMapping("/register/{userid}")
	public ResponseEntity<UserRegisterDTO> RegisterUser(@PathVariable String userid) {
		UserRegisterDTO register = UserMapper.UserDTOconvertToRegisterDTO(userRepository.findById(userid).get());
		return ResponseEntity.ok(register);
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String userid) {
		try {
			UserDTO userdto = UserMapper.UserConvertToUserDTO(userRepository.findById(userid).get());
			return ResponseEntity.ok(userdto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/get-mentor")
	public ResponseEntity<List<UserDTO>> getmenotr(){
		List<User> listuser = userservice.findMentor();
		return ResponseEntity.ok(listuser.stream().distinct().map(u -> UserMapper.UserConvertToUserDTO(u)).collect(Collectors.toList()));
	}

}
