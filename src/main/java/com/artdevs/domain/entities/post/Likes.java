package com.artdevs.domain.entities.post;

import com.artdevs.domain.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private long count=0;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User userLikeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postId")

	private Post postLikeId;
}
