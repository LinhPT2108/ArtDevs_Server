package com.artdevs.dto.post;

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
	private String namePrivacy;
	private boolean status;
	private String createDate;
    private int privacyPostId;
}
