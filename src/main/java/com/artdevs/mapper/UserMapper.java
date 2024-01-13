package com.artdevs.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.UserDTO;
import com.artdevs.dto.UserRegisterDTO;
import com.artdevs.repositories.user.PrograminglanguageRepository;
import com.artdevs.repositories.user.SkillRepository;
import com.artdevs.repositories.user.UserRepository;

public class UserMapper {
	
	@Autowired SkillRepository skillrep;
	
	
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	public static UserDTO UserConvertToUserDTO(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		userDTO.setListComment(null);
		userDTO.setListLike(null);
		userDTO.setListShare(null);
		userDTO.setListReport(null);
		userDTO.setUserForm(null);
		userDTO.setUserTo(null);
		userDTO.setListPostOfUser(null);
		userDTO.setListSkillOfUser(getSkill(user));
		userDTO.setListDemandOfUser(getDemand(user));
		userDTO.setListMethod(null);
		userDTO.setUserTransition1(null);
		userDTO.setUserTransition2(null);
		userDTO.setUserRelation1(null);
		userDTO.setUserRelation2(null);
		userDTO.setUserAction(null);
		return userDTO;
	}
	
	
	public static User UserDTOconvertToUser(UserDTO userDTO) {
		User user= modelMapper.map(userDTO, User.class);
		user.setRole(userDTO.getRole());
		return user;
	}
	
	public static UserDTO UserRegisterConvertToUserDTO(UserRegisterDTO RegisterDTO) {
		UserDTO userDTO = modelMapper.map(RegisterDTO, UserDTO.class);
		userDTO.setListComment(null);
		userDTO.setListLike(null);
		userDTO.setListShare(null);
		userDTO.setListReport(null);
		userDTO.setUserForm(null);
		userDTO.setUserTo(null);
		userDTO.setListPostOfUser(null);
		userDTO.setListSkillOfUser(null);
		userDTO.setListDemandOfUser(null);
		userDTO.setListMethod(null);
		userDTO.setUserTransition1(null);
		userDTO.setUserTransition2(null);
		userDTO.setUserRelation1(null);
		userDTO.setUserRelation2(null);
		userDTO.setUserAction(null);
		return userDTO;
	}
	public static UserRegisterDTO UserDTOconvertToRegisterDTO(User user) {
		UserRegisterDTO registerDTO = modelMapper.map(user, UserRegisterDTO.class);
		registerDTO.setListSkillOfUser(getSkill(user));
		registerDTO.setListDemandOfUser(getDemand(user));
		return registerDTO;
	}
	
	public static User RegisterDTOconvertToUser(UserRegisterDTO RegisterDTO,PrograminglanguageRepository progamingrepository,UserRepository userrep) {
		User user = modelMapper.map(RegisterDTO, User.class);
		user.setUserSkill(null);
		user.setUserDemand(null);
		return user;
	}
	
	private static List<String> getSkill(User user) {
		return user.getUserSkill().stream().map(Skill -> Skill.getLanguage().getLanguageName())
				.collect(Collectors.toList());
	}
	
	private static List<String> getDemand(User user) {
		return user.getUserDemand().stream().map(Demand -> Demand.getLanguage().getLanguageName())
				.collect(Collectors.toList());
	}
	
//	private static List<Skill> setSkill(UserRegisterDTO RegisterDTO,PrograminglanguageRepository progamingrepository,UserRepository userrep) {
//		List<Skill> listSkill = new ArrayList<>();
//		Skill newskill = new Skill();
//		for (String skillname : RegisterDTO.getListSkillOfUser()) {
//			newskill.setUser(userrep.getById(RegisterDTO.getUserId()));
//			newskill.setLanguage(progamingrepository.findByLanguageName(skillname));
//			listSkill.add(newskill);
//		}
//		System.out.println(listSkill.size());
//		return listSkill;
//	}
	
	
}
