package com.artdevs.domain.entities.post;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Nationalized
	@Column
	private String reportDetail;

	@Column
	private int count;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User userReportId;

	@ManyToOne
	@JoinColumn(name = "postId")
	private Post postReportId;
}
