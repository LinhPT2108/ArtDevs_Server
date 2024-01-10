package com.artdevs.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.MethodPay;
import com.artdevs.domain.entities.user.TransitionInfo;
import com.artdevs.domain.entities.user.Wallet;
import com.artdevs.dto.WalletDTO;

public class WalletMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static WalletDTO convertToWalletDTO(Wallet wallet) {
        WalletDTO walletDTO = modelMapper.map(wallet, WalletDTO.class);
        wallet.setTrainsition(getListTransitionInfo(wallet));
        wallet.setWalletMethodPay(getMethodPays(wallet));
        return walletDTO;
    }

    public static Wallet convertToWallet(WalletDTO walletDTO) {
        Wallet wallet = modelMapper.map(walletDTO, Wallet.class);
        return wallet;
    }

    private static List<TransitionInfo> getListTransitionInfo(Wallet wallet) {
        return wallet.getTrainsition().stream()
                .map(tran -> new TransitionInfo(tran.getId(), tran.getPrice_match(), tran.getTimeTransiton(),
                        tran.getUser1(), tran.getUser2(), wallet))
                .collect(Collectors.toList());
    }

    private static List<MethodPay> getMethodPays(Wallet wallet) {
        return wallet.getWalletMethodPay().stream().map(method -> new MethodPay(method.getId(), method.getPayName(),
                method.getUser(), method.getWallet()))
                .collect(Collectors.toList());
    }
}
