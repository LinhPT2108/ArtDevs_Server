package com.artdevs.dto.post;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrivacyPostDetailDTO {
	private Long Id;
	
	private Date createDate = new Date();
	
	private boolean status;
}
