package com.artdevs.restcontroller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.User;
import com.artdevs.domain.entities.user.Wallet;
import com.artdevs.dto.UserRegisterDTO;
import com.artdevs.dto.transition.TransitionInfoDTO;
import com.artdevs.dto.user.MentorDTO;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.mapper.TransitionInfoMapper;
import com.artdevs.mapper.UserMapper;
import com.artdevs.repositories.user.DemandRepository;
import com.artdevs.repositories.user.PrograminglanguageRepository;
import com.artdevs.repositories.user.SkillRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.UserService;
import com.artdevs.services.WalletService;
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
	PrograminglanguageRepository programingrepositories;

	@Autowired 
	WalletService walletservice;
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
		User userregister = userRepository.findByEmail(RegisterDTO.getEmail()).orElse(null);
		if( userregister ==null) {
			
			User user = userRepository.save(UserMapper.RegisterDTOconvertToUser(RegisterDTO));
			Wallet wallet = new Wallet();
			wallet.setSurplus(0);
			wallet.setUser(user);
			walletservice.saveWallet(wallet);
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
		}else {
			return ResponseEntity.notFound().build();
		}
	
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
	public ResponseEntity<List<MentorDTO>> getmenotr(){
		List<User> listuser = userservice.findMentor();
		return ResponseEntity.ok(listuser.stream().distinct().map(u -> UserMapper.UserConvertToMentorDTO(u)).collect(Collectors.toList()));
	}
	
	@GetMapping("/get-match-from-user")
	public ResponseEntity<List<UserDTO>> getmatchfromuser(){
		List<User> listuser = userservice.getListMatchbyUser();
		return ResponseEntity.ok(listuser.stream().distinct().map(u -> UserMapper.UserConvertToUserDTO(u)).collect(Collectors.toList()));
	}
	
	@GetMapping("/get-mentor-isready")
	public ResponseEntity<List<MentorDTO>> getmenotrisready(){
		List<User> listuser = userservice.FindMentorIsReady();
		return ResponseEntity.ok(listuser.stream().distinct().map(u -> UserMapper.UserConvertToMentorDTO(u)).collect(Collectors.toList()));
	}
	
	@PostMapping("/send-match/{mentorid}")
	public ResponseEntity<?> sendmatchmentor(@PathVariable("mentorid") String mentorid){
		return ResponseEntity.ok(userservice.SendMatchMentor(mentorid));
	}
	
	@PostMapping("/accept-match/{userid}")
	public ResponseEntity<?> acceptmatchuser(@PathVariable("userid") String userid){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return ResponseEntity.ok(userservice.AcceptMatchMentor(userid));
	}
	
	@PostMapping("/cancel-sendmatch/{userid}")
	public ResponseEntity<?> cancelsendmatch(@PathVariable("userid") String userid){
		return ResponseEntity.ok(userservice.CancelSendMatchMentor(userid));
	}
	@PostMapping("/set-isready")
	public ResponseEntity<MentorDTO> setIsReady(){
		return ResponseEntity.ok(UserMapper.UserConvertToMentorDTO(userservice.setIsReady()));
	}
	
	@GetMapping("/get-profile")
	public ResponseEntity<?> Getprofile(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
		if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("mentor"))) {
			MentorDTO result = UserMapper.UserConvertToMentorDTO(userservice.findByEmail(auth.getName()));
			return	ResponseEntity.ok(result);
		}else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("user"))) {
			UserDTO result = UserMapper.UserConvertToUserDTO(userservice.findByEmail(auth.getName()));
			return	ResponseEntity.ok(result);
		}
		else {
			return	ResponseEntity.notFound().build();
		}
		
	}
	
	


}
