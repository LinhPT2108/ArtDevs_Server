package com.artdevs.domain.entities.post;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class PrivacyPostDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	@Column
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "privacyPostId")
	private PrivacyPost privacyPost;
	
	@ManyToOne
	@JoinColumn(name = "PostId")
	private Post post;
	
}
