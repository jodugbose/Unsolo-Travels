package com.interswitch.Unsolorockets.service.impl;


import com.interswitch.Unsolorockets.dtos.WalletDto;
import com.interswitch.Unsolorockets.exceptions.CommonsException;
import com.interswitch.Unsolorockets.models.Wallet;
import com.interswitch.Unsolorockets.respository.WalletRepository;
import com.interswitch.Unsolorockets.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.interswitch.Unsolorockets.utils.AppUtils.generateWalletId;

@RequiredArgsConstructor
@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;


    public void createWallet(long userId) throws Exception {
        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(() -> new CommonsException("user already has a wallet", HttpStatus.CONFLICT));
        wallet.setWalletId(generateWalletId());
        wallet.setUserId(userId);
        walletRepository.save(wallet);
    }


    public WalletDto getWallet(Long userId) throws CommonsException {
        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(() -> new CommonsException("user does not have a wallet", HttpStatus.NOT_FOUND));
        WalletDto walletDto = new WalletDto();
        walletDto.setBalance(wallet.getBalance());
        walletDto.setWalletId(wallet.getWalletId());
        return walletDto;
    }
}
