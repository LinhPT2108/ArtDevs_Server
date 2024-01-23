package com.artdevs.restcontroller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Wallet;
import com.artdevs.dto.transition.WalletDTO;
import com.artdevs.mapper.WalletMapper;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.repositories.user.WalletRepository;
import com.artdevs.services.WalletService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class WalletRestController {
    @Autowired
    WalletService walletService;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> postWallet(@RequestBody WalletDTO walletDTO) {

        return ResponseEntity.ok(walletService.saveWallet(WalletMapper.convertToWallet(walletDTO)));
    }

    @GetMapping("/wallet")
    public ResponseEntity<List<WalletDTO>> getWallet() {
        List<WalletDTO> listWalletDTO = new ArrayList<>();
        List<Wallet> listWallet = walletRepository.findAll();
        for (Wallet wallet : listWallet) {
            listWalletDTO.add(WalletMapper.convertToWalletDTO(wallet));
        }
        return ResponseEntity.ok(listWalletDTO);
    }
}
