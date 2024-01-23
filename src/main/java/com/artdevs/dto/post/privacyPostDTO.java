package com.artdevs.dto.post;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class privacyPostDTO {
	private Long Id;

	private String namePrivacy;

	private List<PrivacyPostDetailDTO> listPrivacyPostDetails;
}
