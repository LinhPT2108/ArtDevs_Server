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
public class DetailHashtagToGetDTO {
	private String hashtagText;
	private String id;
	private Double countHashtagOfDetail;
}
