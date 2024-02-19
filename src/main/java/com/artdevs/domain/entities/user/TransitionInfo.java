package com.artdevs.domain.entities.user;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class TransitionInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private long price_match;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timeTransiton;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userTransition1")
	private User Userlogin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userTransition2")
	private User Mentor;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "walletId")
//	private Wallet walletOfUser;
}
