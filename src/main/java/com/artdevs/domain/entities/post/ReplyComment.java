package com.artdevs.domain.entities.post;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReplyComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Nationalized
	@Column
	private String content;

	@Column
	private String imageUrl;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timeComment;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User userReportId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userReceive")
	private User userReceive;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commentId")
	private Comment commentId;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pictureOfReplyCommentId")
	private List<PictureOfComment> listPictureOfComment;
	
	
}
