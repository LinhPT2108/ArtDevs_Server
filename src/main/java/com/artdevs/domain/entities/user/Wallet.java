package com.artdevs.domain.entities.user;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private long surplus;

	@OneToMany(mappedBy = "wallet")
	private List<MethodPay> walletMethodPay;

	@OneToMany(mappedBy = "walletOfUser")
	private List<TransitionInfo> trainsition;

}
