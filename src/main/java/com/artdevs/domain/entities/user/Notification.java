package com.artdevs.domain.entities.user;

import java.util.Date;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@Nationalized
	private String message;
	
	@Column
	@Nationalized
	private String type;
	
	@Column
	private String postId;
	
	@Column
	private String shareId;
	
	@Column 
	private boolean isRead = false;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sender")
	private User sender;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "receiver")
	private User receiver;
	
	
	
}
