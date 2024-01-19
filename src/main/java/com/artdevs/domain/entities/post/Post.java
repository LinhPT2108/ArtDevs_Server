package com.artdevs.domain.entities.post;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Nationalized
	@Column
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date time;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timelineUserId;
	
	@Column
	private boolean isDel;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "postLikeId")
	private List<Likes> listLikePost;

	@JsonIgnore
	@OneToMany(mappedBy = "postShareId")
	private List<Share> listSharePost;

	@JsonIgnore
	@OneToMany(mappedBy = "postReportId")
	private List<Report> listReportPost;

	@JsonIgnore
	@OneToMany(mappedBy = "postCommentId")
	private List<Comment> listCommentPost;

	@JsonIgnore
	@OneToMany(mappedBy = "postImage")
	private List<ImageOfPost> listImage;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "typepostId")
	private TypePost postType;

	@JsonIgnore
	@OneToMany(mappedBy = "postHashtag")
	private List<HashTag> listHashtag;
	
	@JsonIgnore
	@OneToMany(mappedBy = "post")
	private List<PrivacyPostDetail> privacyPostDetails;
}
