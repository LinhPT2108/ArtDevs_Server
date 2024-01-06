package com.artdevs.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Wallet;
import com.artdevs.dto.WalletDTO;
import com.artdevs.mapper.WalletMapper;
import com.artdevs.repositories.user.WalletRepository;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class WalletRestController {
    @Autowired
    WalletRepository walletRepository;

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> postWallet(@RequestBody WalletDTO walletDTO) {
        return ResponseEntity.ok(walletRepository.save(WalletMapper.convertToWallet(walletDTO)));
    }

    @GetMapping("/wallet")
    public ResponseEntity<List<Wallet>> getWallet() {
        return ResponseEntity.ok(walletRepository.findAll());
    }
}
