package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.user.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

}
