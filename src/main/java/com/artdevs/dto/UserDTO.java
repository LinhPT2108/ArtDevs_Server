package com.artdevs.dto;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String userId;

	private boolean isAccountNonExpired;

	private boolean isAccountNonLocked;

	@Nationalized
	private String address;

	private String BackgroundImageUrl;

	@Nationalized
	private String city;

	@Nationalized
	private String district;

	@Nationalized
	private String ward;

	private boolean isCreadentialsNonExprired;

	private boolean isDelete;

	private String email;

	private boolean isEnabled;

	@Nationalized
	private String firstName;

	@Nationalized
	private String lastName;

	@Nationalized
	private String middleName;

	private String isOnline;

	private String password;

	private String profilePicUrl;

	@Nationalized
	private String username;
}
