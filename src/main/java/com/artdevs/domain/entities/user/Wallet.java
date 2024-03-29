package com.artdevs.domain.entities.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private long surplus;

	@JsonIgnore
	@OneToMany(mappedBy = "wallet")
	private List<MethodPay> walletMethodPay;
	
	@OneToOne
    @JoinColumn(name = "userId")
    private User user;
//	@JsonIgnore
//	@OneToMany(mappedBy = "walletOfUser")
//	private List<TransitionInfo> trainsition;

}
