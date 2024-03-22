package com.artdevs.dto.CustomDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationDTO {
	 private String email;
	    private int verificationCode;
}
