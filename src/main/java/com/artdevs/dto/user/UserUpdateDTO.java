package com.artdevs.dto.user;

import java.util.Date;
import java.util.List;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.domain.entities.user.Role;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
	private String UserId;
	private String email;
	private Role role;
	private Date createDate;
	private boolean accountNonLocked;
	private String userPictureAvatar;
}
