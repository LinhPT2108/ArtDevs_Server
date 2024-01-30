package com.artdevs.domain.entities.user;

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
public class Demand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Nationalized
	@Column
	private String description;

	@Column
	private String desiredTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "programinglanguageId")
	private ProgramingLanguage language;
}
