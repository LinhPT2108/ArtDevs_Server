package com.artdevs.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.UserRegisterDTO;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.repositories.user.SkillRepository;


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
	public static User RegisterDTOconvertToUser(UserRegisterDTO RegisterDTO) {
		User user = modelMapper.map(RegisterDTO, User.class);
		user.setUserSkill(null);
		user.setUserDemand(null);
		return user;
	}
	
//	public static User RegisterDTOconvertToUser2(UserRegisterDTO RegisterDTO,SkillRepository skillrep ,UserRepository userrep) {
//		User user = modelMapper.map(RegisterDTO, User.class);
//		user.setUserSkill(setSkill(RegisterDTO,skillrep,userrep));
//		user.setUserDemand(null);
//		return user;
//	}
	
	private static List<String> getSkill(User user) {
		return user.getUserSkill().stream().map(Skill -> Skill.getLanguage().getLanguageName())
				.collect(Collectors.toList());
	}
	
	private static List<String> getDemand(User user) {
		return user.getUserDemand().stream().map(Demand -> Demand.getLanguage().getLanguageName())
				.collect(Collectors.toList());
	}
	
//	private static List<Skill> setSkill(UserRegisterDTO RegisterDTO,SkillRepository skillrep ,UserRepository userrep) {
//		List<Skill> listSkill = skillrep.findByUser(userrep.getById(RegisterDTO.getUserId()));
//		return listSkill;
//	}
	
	
}
