package com.artdevs.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.MethodPay;
import com.artdevs.domain.entities.user.TransitionInfo;
import com.artdevs.domain.entities.user.Wallet;
import com.artdevs.dto.transition.MethodPayDTO;
import com.artdevs.dto.transition.TransitionInfoDTO;
import com.artdevs.dto.transition.WalletDTO;

public class WalletMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static WalletDTO convertToWalletDTO(Wallet wallet) {
        WalletDTO walletDTO = modelMapper.map(wallet, WalletDTO.class);
        walletDTO.setListTransitionInfo(getListTransitionInfo(wallet));
        walletDTO.setListMethodPay(getMethodPays(wallet));
        return walletDTO;
    }

    public static Wallet convertToWallet(WalletDTO walletDTO) {
        Wallet wallet = modelMapper.map(walletDTO, Wallet.class);
        return wallet;
    }

    private static List<TransitionInfoDTO> getListTransitionInfo(Wallet wallet) {
        List<TransitionInfoDTO> transitionInfoDTO = new ArrayList<>();
        List<TransitionInfo> transitionInfos = wallet.getTrainsition();
        for (TransitionInfo transitionInfo : transitionInfos) {
            transitionInfoDTO.add(TransitionInfoMapper.convertToTransitionInfoDTO(transitionInfo));
        }
        return transitionInfoDTO;
    }

    private static List<MethodPayDTO> getMethodPays(Wallet wallet) {
        List<MethodPayDTO> methodPayDTO = new ArrayList<>();
        List<MethodPay> methodPays = wallet.getWalletMethodPay();
        for (MethodPay methodPay : methodPays) {
            methodPayDTO.add(MethodPayMapper.convertToMethodPayDTO(methodPay));
        }
        return methodPayDTO;
    }
}
