package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.User;
import com.artdevs.domain.entities.user.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
	Wallet findByUser(User userId);
}
