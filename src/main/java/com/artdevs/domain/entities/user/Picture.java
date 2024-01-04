package com.artdevs.domain.entities.user;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

}
