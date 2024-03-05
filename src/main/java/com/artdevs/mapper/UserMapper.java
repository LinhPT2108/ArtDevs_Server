package com.artdevs.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.UserRegisterDTO;
import com.artdevs.dto.CustomDTO.UserGetRelationDTO;
import com.artdevs.dto.user.MentorDTO;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.repositories.user.SkillRepository;
import com.artdevs.utils.CustomContructor;

public class UserMapper {

	@Autowired
	SkillRepository skillrep;

	private static final ModelMapper modelMapper = new ModelMapper();

	public static UserDTO UserConvertToUserDTO(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		userDTO.setListDemandOfUser(getDemand(user));
		userDTO.setBackgroundImageUrl(getAvatar(user, false));
		userDTO.setProfileImageUrl(getAvatar(user, true));
		return userDTO;
	}

	public static User UserDTOconvertToUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user.setRole(userDTO.getRole());
		return user;
	}

	public static UserDTO UserRegisterConvertToUserDTO(UserRegisterDTO RegisterDTO) {
		UserDTO userDTO = modelMapper.map(RegisterDTO, UserDTO.class);
		userDTO.setListDemandOfUser(RegisterDTO.getListDemandOfUser());
		return userDTO;
	}

//	public static UserRegisterDTO UserDTOconvertToRegisterDTO(User user) {
//		UserRegisterDTO registerDTO = modelMapper.map(user, UserRegisterDTO.class);
//		registerDTO.setListSkillOfUser(getSkill(user));
//		registerDTO.setListDemandOfUser(getDemand(user));
//		return registerDTO;
//	}
//	

	public static User RegisterDTOconvertToUser(UserRegisterDTO RegisterDTO) {
		RegisterDTO.setPassword(new BCryptPasswordEncoder().encode(RegisterDTO.getPassword()));
		User user = modelMapper.map(RegisterDTO, User.class);
		user.setUserSkill(null);
		user.setUserDemand(null);
		return user;
	}

	public static MentorDTO UserConvertToMentorDTO(User user) {
		MentorDTO mentorDTO = modelMapper.map(user, MentorDTO.class);
		mentorDTO.setListSkillOfMentor(getSkill(user));

		return mentorDTO;
	}

	// public static User RegisterDTOconvertToUser2(UserRegisterDTO
	// RegisterDTO,SkillRepository skillrep ,UserRepository userrep) {
	// User user = modelMapper.map(RegisterDTO, User.class);
	// user.setUserSkill(setSkill(RegisterDTO,skillrep,userrep));
	// user.setUserDemand(null);
	// return user;
	// }

	private static List<String> getSkill(User user) {
		return user.getUserSkill().stream().map(Skill -> Skill.getLanguage().getLanguageName())
				.collect(Collectors.toList());
	}

	private static List<String> getDemand(User user) {
		return user.getUserDemand().stream().map(Demand -> Demand.getLanguage().getLanguageName())
				.collect(Collectors.toList());
	}

	// private static List<Skill> setSkill(UserRegisterDTO
	// RegisterDTO,SkillRepository skillrep ,UserRepository userrep) {
	// List<Skill> listSkill =
	// skillrep.findByUser(userrep.getById(RegisterDTO.getUserId()));
	// return listSkill;
	// }
	
	public static final String getAvatar(User user, boolean positon) {
		List<Picture> listPic = !user.getUserPicture().isEmpty() ?user.getUserPicture().stream()
				.sorted((o1, o2) -> o2.getTime().compareTo(o1.getTime()))
				.filter(t -> t.isPositionOfPicture() == positon).toList():null;
		return listPic!=null  ? listPic.get(0).getImageUrl() : null;
	}

	 public static UserGetRelationDTO UserConvertToUserGetDTO (User user) {
		 
		 
		 UserGetRelationDTO result = modelMapper.map(user, UserGetRelationDTO.class);
		 result.setProfilePicUrl(getAvatar(user,true));
		 result.setFullname(user.getFirstName()+" " + user.getMiddleName() + " " + user.getLastName());
		 return result;
	 }
	 
}
