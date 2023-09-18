package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.dtos.TransferResponse;
import com.interswitch.Unsolorockets.dtos.requests.CreateWalletRequest;
import com.interswitch.Unsolorockets.dtos.requests.TransferRequestDto;
import com.interswitch.Unsolorockets.exceptions.CommonsException;
import com.interswitch.Unsolorockets.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;


    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createWallet(@PathVariable Long userId, @RequestBody CreateWalletRequest createWalletRequest) throws Exception {
        walletService.createWallet(userId, createWalletRequest);
    }

    @GetMapping("wallet-info/{userId}")
    public ResponseEntity<?> getUserWallet(@PathVariable Long userId) throws CommonsException {
        return new ResponseEntity<>(walletService.getWallet(userId), HttpStatus.OK);
    }

    @PostMapping("/transfer/{userId}")
    public ResponseEntity<?> transfer(@PathVariable Long userId, @RequestBody TransferRequestDto transferRequestDto) throws CommonsException, IOException {
        TransferResponse response = walletService.transfer(userId, transferRequestDto);
        return ResponseEntity.ok(response);
    }
}