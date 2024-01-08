package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Wallet;
import com.artdevs.dto.WalletDTO;

public class WalletMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static WalletDTO convertToWalletDTO(Wallet wallet){
        WalletDTO walletDTO = modelMapper.map(wallet, WalletDTO.class);
        return walletDTO;
    }

    public static Wallet convertToWallet(WalletDTO walletDTO){
        Wallet wallet = modelMapper.map(walletDTO, Wallet.class);
        return wallet;
    }
}
