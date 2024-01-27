package com.artdevs.domain.entities.user;

import java.util.Date;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	@Column
	private String cloudinaryPublicId;

	@Nationalized
	@Size(max = 255, message = "Description must not exceed 255 characters")
	@Column
	private String description;

	@NotBlank(message = "Image URL must not be blank")
	@Column
	private String imageUrl;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date time;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@Column
	private boolean positionOfPicture; // true (1): ảnh đại diện; false (0): ảnh bìa

}
