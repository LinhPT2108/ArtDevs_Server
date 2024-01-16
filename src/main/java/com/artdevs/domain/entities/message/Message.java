package com.artdevs.domain.entities.message;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "realationshipId")
	private RelationShip relationShipId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userForm")
	private User formUserId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userTo")
	private User toUserId;

	@JsonIgnore
	@OneToMany(mappedBy = "message")
	private List<PictureOfMessage> pictureOfMessages;

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", content=" + content + ", subject=" + subject + ", timeMessage="
				+ timeMessage + "]";
	}

}
