package com.artdevs.dto.user;

import java.util.Date;
import java.util.List;

import com.artdevs.domain.entities.user.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorDTO {
	private String userId;

	private List<PictureDTO> backgroudimage;

	private String city;

	private String district;

	private String ward;

	private boolean isDelete;

	private String email;

	private String firstName;

	private String lastName;

	private String middleName;

	private Date birthday;

	private Integer Gender;

	private String isOnline;

	private String password;

	private List<PictureDTO> profileimage;

	private String username;

	private Role role;

	private Boolean isReady;
	
	private Integer PriceMatch;

	private List<String> listSkillOfMentor;
}
