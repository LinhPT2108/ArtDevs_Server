package com.artdevs.dto.CustomDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordFormDTO {
	 private  String oldPassword;
	 private  String newPassword;
	 private  String confirmPassword;
}
