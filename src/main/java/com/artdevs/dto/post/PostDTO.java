package com.artdevs.dto.post;

import java.util.Date;
import java.util.List;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Report;
import com.artdevs.domain.entities.post.Share;
import com.artdevs.domain.entities.post.TypePost;
import com.artdevs.domain.entities.user.User;

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
	private String imageUrl;
	private String content;
	private Date time;
	private Date timelineUserId;
//	private User user;
//	private List<Likes> listLikePost;
//	private List<Share> listSharePost;
//	private List<Report> listReportPost;
//	private List<Comment> listCommentPost;
//	private List<ImageOfPost> listImage;
//	private TypePost postType;
//	private List<HashTag> listHashtag;
}
