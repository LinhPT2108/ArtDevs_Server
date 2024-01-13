package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.Wallet;
import com.artdevs.repositories.user.WalletRepository;
import com.artdevs.services.WalletService;

public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public Wallet findWalletById(Integer walletId) {
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        return walletOptional.orElse(null);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteWallet(Wallet wallet) {
        walletRepository.delete(wallet);
    }

}
