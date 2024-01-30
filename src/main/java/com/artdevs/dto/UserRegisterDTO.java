package com.artdevs.dto;

import java.util.Date;
import java.util.List;

import com.artdevs.domain.entities.user.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class UserRegisterDTO {

	private String userId;

	private String address;

	private String BackgroundImageUrl;

	private String city;

	private String district;

	private String ward;

	private String email;

	private String firstName;

	private String lastName;

	private String middleName;
	
	private Date birthday;
	
	private int gender;

	private String password;

	private String profilePicUrl;

	private String username;

	private Role role;

	private List<String> listDemandOfUser;

	private List<String> listSkillOfUser;

}
