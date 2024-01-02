package com.artdevs.domain.entities.post;

import java.util.Date;
import java.util.List;

import com.artdevs.domain.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
	@Id
	private String postId;
	
	@Column
	private String imageUrl;
	
	@Column
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date time;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timelineUserId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@OneToMany(mappedBy = "postLikeId")
	private List<Likes> listLikePost;
	
	@OneToMany(mappedBy = "postShareId")
	private List<Share> listSharePost;
	
	@OneToMany(mappedBy = "postReportId")
	private List<Report> listReportPost;
	
	@OneToMany(mappedBy = "postCommentId")
	private List<Comment> listCommentPost;
	
	@OneToMany(mappedBy = "postImage")
	private List<ImageOfPost> listImage;
	
	@ManyToOne
	@JoinColumn(name="typepostId")
	private TypePost postType;
	
	@OneToMany(mappedBy = "postHashtag")
	private List<HashTag>  listHashtag;
}
