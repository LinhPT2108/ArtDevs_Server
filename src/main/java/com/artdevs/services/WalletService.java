package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.user.User;
import com.artdevs.domain.entities.user.Wallet;

public interface WalletService {
    Wallet findWalletById(Integer walletId);

    List<Wallet> findAll();

    Wallet saveWallet(Wallet wallet);

    Wallet updateWallet(Wallet wallet);

    void deleteWallet(Wallet wallet);
    
    Wallet FindByUser(User user);
}
