package com.artdevs.domain.entities.post;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Post {
	@Id
	private String postId;

	@Nationalized
	@Column
	private String content;

	
	@Column
	private Date time;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timelineUserId;

	@Column
	private boolean isDel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "postLikeId")
	private List<Likes> listLikePost;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "postShareId")
	private List<Share> listSharePost;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "postReportId")
	private List<Report> listReportPost;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "postCommentId")
	private List<Comment> listCommentPost;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "postImage")
	private List<ImageOfPost> listImage;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "postHashtag")
	private List<HashTag> listHashtag;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "post")
	private List<PrivacyPostDetail> privacyPostDetails;
}
