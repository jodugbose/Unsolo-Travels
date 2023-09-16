package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.exceptions.CommonsException;
import com.interswitch.Unsolorockets.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;


    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createWallet(@PathVariable Long userId) throws Exception {
        walletService.createWallet(userId);
    }

    @GetMapping("wallet-info/{userId}")
    public ResponseEntity<?> getUserWallet(@PathVariable Long userId) throws CommonsException {
        return new ResponseEntity<>(walletService.getWallet(userId), HttpStatus.OK);
    }
}