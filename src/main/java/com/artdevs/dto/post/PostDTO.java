package com.artdevs.dto.post;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.TypePost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	private String postId;
	private String content;
	private Date time;
	private Date timelineUserId;
	private String userId;
	private Long totalLike;
	private Long totalShare;
	private List<ReportDTO> listReportPost;
	private Long totalComment;
	private MultipartFile[] listImageofPost;
	private TypePost postType;
	private List<HashTagDTO> listHashtag;
}
