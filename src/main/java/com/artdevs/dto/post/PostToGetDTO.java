package com.artdevs.dto.post;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostToGetDTO {
	private String postId;
	private String content;
	private Date time;
	private Date timelineUserId;
	private UserPostDTO userPost;
	private Long totalLike;
	private Long totalShare;
	private Long totalComment;
	private List<ImageOfPostDTO> listImageofPost;
	private List<PrivacyPostDetailDTO> privacyPostDetails;
	private List<HashTagDTO> listHashtag;
	private boolean isDel;
}
