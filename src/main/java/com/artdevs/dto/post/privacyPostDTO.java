package com.artdevs.dto.post;

import java.util.List;

import com.artdevs.domain.entities.post.PrivacyPostDetail;

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

	private List<PrivacyPostDetail> listPrivacyPostDetails;
}
