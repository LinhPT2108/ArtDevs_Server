package com.artdevs.domain.entities.message;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.user.User;

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
public class RelationShip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	@Column
	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timeRelation;

	@ManyToOne
	@JoinColumn(name = "userAction")
	private User actionUser;

	@ManyToOne
	@JoinColumn(name = "userRelation1")
	private User userOneId;

	@ManyToOne
	@JoinColumn(name = "userRelation2")
	private User userTwoId;

	@OneToMany(mappedBy = "relationShipId")
	private List<Message> relationMessage;

}
