package com.artdevs.domain.entities.post;

import java.util.Date;

import com.artdevs.domain.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String content;
	
	@Column
	private String imageUrl;
	
	@Column
	private long Count;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timeComment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timeUserId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User userReportId;
	
	@ManyToOne
	@JoinColumn(name="postId")
	private Post postCommentId;
}
