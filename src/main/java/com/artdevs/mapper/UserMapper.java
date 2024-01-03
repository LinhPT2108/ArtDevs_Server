package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.UserDTO;

public class UserMapper {
	private static final ModelMapper modelMapper = new ModelMapper();
	
	public static UserDTO convertToUserDTO(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	
	public static User convertToUser(UserDTO userDTO) {
		User user= modelMapper.map(userDTO, User.class);
		return user;
	}
}
