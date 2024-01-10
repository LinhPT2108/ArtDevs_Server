package com.artdevs.domain.entities.message;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Message {
	@Id
	private String messageId;

	@Nationalized
	@Column
	private String content;

	@Nationalized
	@Column
	private String subject;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timeMessage;

	@ManyToOne
	@JoinColumn(name = "realationshipId")
	private RelationShip relationShipId;

	@ManyToOne
	@JoinColumn(name = "userForm")
	private User formUserId;

	@ManyToOne
	@JoinColumn(name = "userTo")
	private User toUserId;
	
	@OneToMany(mappedBy = "message")
	private List<PictureOfMessage> pictureOfMessages;

}
