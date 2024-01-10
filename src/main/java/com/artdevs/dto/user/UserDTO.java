package com.artdevs.dto.user;

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

	private String BackgroundImageUrl;

	private String city;

	private String district;

	private String ward;

	private boolean isCreadentialsNonExprired;

	private boolean isDelete;

	private String email;

	private boolean isEnabled;

	private String firstName;

	private String lastName;

	private String middleName;

	private String isOnline;

	private String password;

	private String profilePicUrl;

	private String username;
}
