package com.interswitch.Unsolorockets.service.impl;


import com.interswitch.Unsolorockets.dtos.TransferResponse;
import com.interswitch.Unsolorockets.dtos.requests.CreateWalletRequest;
import com.interswitch.Unsolorockets.dtos.requests.TransferRequestDto;
import com.interswitch.Unsolorockets.dtos.responses.WalletDto;
import com.interswitch.Unsolorockets.exceptions.CommonsException;
import com.interswitch.Unsolorockets.models.Traveller;
import com.interswitch.Unsolorockets.models.Wallet;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import com.interswitch.Unsolorockets.respository.WalletRepository;
import com.interswitch.Unsolorockets.service.EmailService;
import com.interswitch.Unsolorockets.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.interswitch.Unsolorockets.utils.AppUtils.generateWalletId;

@RequiredArgsConstructor
@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailService emailService;

    private final TravellerRepository travellerRepository;

    private final ExecutorService emailExecutor = Executors.newFixedThreadPool(2);


    public void createWallet(long userId, CreateWalletRequest createWalletRequest) throws Exception {
        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(() -> new CommonsException("user already has a wallet", HttpStatus.CONFLICT));
        wallet.setWalletId(generateWalletId());
        wallet.setUserId(userId);
        wallet.setPin(bCryptPasswordEncoder.encode(createWalletRequest.getPin()));
        walletRepository.save(wallet);
    }


    public WalletDto getWallet(Long userId) throws CommonsException {
        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(() -> new CommonsException("user does not have a wallet", HttpStatus.NOT_FOUND));
        WalletDto walletDto = new WalletDto();
        walletDto.setBalance(wallet.getBalance());
        walletDto.setWalletId(wallet.getWalletId());
        return walletDto;
    }

    @Override
    public TransferResponse transfer(Long userId, TransferRequestDto transferRequestDto) throws CommonsException, IOException {
        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(() -> new CommonsException("user does not have a wallet", HttpStatus.NOT_FOUND));
        Wallet receiverWallet = walletRepository.findWalletByWalletId(transferRequestDto.getWalletId()).orElseThrow(() -> new CommonsException("wallet does not exist", HttpStatus.NOT_FOUND));
        if (wallet.getBalance().compareTo(transferRequestDto.getAmount()) < 0) {
            throw new CommonsException("Insufficient Balance", HttpStatus.BAD_REQUEST);
        }
        if (!bCryptPasswordEncoder.matches(transferRequestDto.getPin(), wallet.getPin())) {
            throw new CommonsException("Pin not correct", HttpStatus.BAD_REQUEST);
        }
        wallet.setBalance(wallet.getBalance().subtract(transferRequestDto.getAmount()));
        receiverWallet.setBalance(receiverWallet.getBalance().add(transferRequestDto.getAmount()));

        walletRepository.save(wallet);
        walletRepository.save(receiverWallet);

        Traveller sender = travellerRepository.findById(wallet.getUserId()).orElseThrow(() -> new CommonsException("user does not exist", HttpStatus.NOT_FOUND));
        Traveller receiver = travellerRepository.findById(receiverWallet.getUserId()).orElseThrow(() -> new CommonsException("user does not exist", HttpStatus.NOT_FOUND));


        emailExecutor.submit(() -> {
            try {
                emailService.sendMail(sender.getEmail(), "DEBIT ALERT", "", "context/html");
            } catch (IOException e) {
                log.error("an error occurred [{}] ", e.getMessage());
            }
        });

        emailExecutor.submit(() -> {
            try {
                emailService.sendMail(receiver.getEmail(), "CREDIT ALERT", "", "context/html");
            } catch (IOException e) {
                log.error("an error occurred [{}] ", e.getMessage());
            }
        });

        emailService.sendMail(sender.getEmail(), "DEBIT ALERT", "", "context/html");

        emailService.sendMail(receiver.getEmail(), "DEBIT ALERT", "", "context/html");

        return TransferResponse.builder()
                .walletId(transferRequestDto.getWalletId())
                .amount(transferRequestDto.getAmount())
                .message("Transfer Successful")
                .build();
    }
}
