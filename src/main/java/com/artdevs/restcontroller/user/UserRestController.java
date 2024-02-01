package com.artdevs.restcontroller.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.config.auth.AuthenticationResponse;
import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.Role;
import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.ErrorResponseDTO;
import com.artdevs.dto.UserRegisterDTO;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.repositories.user.DemandRepository;
import com.artdevs.repositories.user.PrograminglanguageRepository;
import com.artdevs.repositories.user.SkillRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.service.JwtTokenProvider;
import com.artdevs.services.UserService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class UserRestController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userservice;
	@Autowired
	SkillRepository skillrep;

	@Autowired
	DemandRepository demandrepositories;

	@Autowired
	JwtTokenProvider jwtService;

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
	public ResponseEntity<?> RegisterUser(@RequestBody UserRegisterDTO RegisterDTO) {
		System.out.println(">>> check user: ,");
		try {
			User emailExist = userRepository.findByEmail(RegisterDTO.getEmail()).orElse(null);
			System.out.println(">>> check user: ," + emailExist);
			if (emailExist == null) {
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
				UserDTO userdto = UserMapper.UserRegisterConvertToUserDTO(RegisterDTO);
				return ResponseEntity.ok(userdto);
			} else {
				ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
						"Email đã tồn tại !");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
			}
		} catch (Exception e) {
			ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
					"Đăng ký thất bại: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@GetMapping("/user-by-email")
	public ResponseEntity<?> RegisterUser(@RequestParam String email) {
		try {
			User emailExist = userRepository.findByEmail(email).orElse(null);
			if (emailExist != null) {
				ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
						"Email đã tồn tại !");
				return ResponseEntity.ok(errorResponse);
			} else {
				ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.OK.value(), "");
				return ResponseEntity.ok(errorResponse);
			}
		} catch (Exception e) {
			ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.OK.value(), "");
			return ResponseEntity.ok(errorResponse);
		}
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String userid) {
		try {
			UserDTO userdto = UserMapper.UserConvertToUserDTO(userRepository.findByEmail(userid).get());
			return ResponseEntity.ok(userdto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/get-mentor")
	public ResponseEntity<List<UserDTO>> getmenotr() {
		List<User> listuser = userservice.findMentor();
		return ResponseEntity.ok(
				listuser.stream().distinct().map(u -> UserMapper.UserConvertToUserDTO(u)).collect(Collectors.toList()));
	}

	@GetMapping("/user-social")
	public ResponseEntity<AuthenticationResponse> getUserByEmailAndProvider(@RequestParam("email") String email,
			@RequestParam("provider") String provider) {
		User user = userRepository.findByEmailAndProvider(email, provider).get();
		UserDTO userdto = UserMapper.UserConvertToUserDTO(user);

		Role role = null;
		if (user != null) {
			role = user.getRole();
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		String jwtToken = jwtService.generateToken(user, authorities);
		String jwtRefeshToken = jwtService.generateRefeshToken(user, authorities);
		return ResponseEntity.ok(
				AuthenticationResponse.builder().token(jwtToken).refeshToken(jwtRefeshToken).userdto(userdto).build());
	}

}
