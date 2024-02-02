package com.artdevs.domain.entities.user;

import java.util.Date;

import org.hibernate.annotations.Nationalized;

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
public class Star {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@Nationalized
	private String content;
	
	@Column
	private double star;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userSend")
	private User userSend;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userReceive")
	private User userReceive;
	
	
}
