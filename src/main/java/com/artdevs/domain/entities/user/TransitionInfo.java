package com.artdevs.domain.entities.user;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class TransitionInfo {
	@Id
	private String id;
	
	@Column
	private long price_match;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timeTransiton;
	
	@ManyToOne
	@JoinColumn(name="userTransition1")
	private User user1;
	
	@ManyToOne
	@JoinColumn(name="userTransition2")
	private User user2;
	
	@ManyToOne
	@JoinColumn(name="walletId")
	private Wallet walletOfUser;
}
