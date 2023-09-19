package com.interswitch.Unsolorockets.service.impl;

import com.interswitch.Unsolorockets.dtos.TransferChargeDto;
import com.interswitch.Unsolorockets.dtos.requests.CreateWalletRequest;
import com.interswitch.Unsolorockets.dtos.requests.TransferRequestDto;
import com.interswitch.Unsolorockets.dtos.responses.TransferResponse;
import com.interswitch.Unsolorockets.dtos.responses.WalletDto;
import com.interswitch.Unsolorockets.exceptions.CommonsException;
import com.interswitch.Unsolorockets.models.Traveller;
import com.interswitch.Unsolorockets.models.Wallet;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import com.interswitch.Unsolorockets.respository.WalletRepository;
import com.interswitch.Unsolorockets.security.IPasswordEncoder;
import com.interswitch.Unsolorockets.service.EmailService;
import com.interswitch.Unsolorockets.service.TransferChargeService;
import com.interswitch.Unsolorockets.service.TransferChargeTransactionService;
import com.interswitch.Unsolorockets.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static com.interswitch.Unsolorockets.utils.AppUtils.generateWalletId;

@RequiredArgsConstructor
@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final EmailService emailService;

    private final TravellerRepository travellerRepository;

    private final TransferChargeService transferChargeService;

    private final TransferChargeTransactionService transferChargeTransactionService;

    private final IPasswordEncoder passwordEncoder;



    public void createWallet(long userId, CreateWalletRequest createWalletRequest) throws Exception {
        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(() -> new CommonsException("user already has a wallet", HttpStatus.CONFLICT));
        wallet.setWalletId(generateWalletId());
        wallet.setUserId(userId);
        wallet.setPin(passwordEncoder.encode(createWalletRequest.getPin()));
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
    @Transactional
    public TransferResponse transfer(Long userId, TransferRequestDto transferRequestDto) throws CommonsException, IOException {
        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(() -> new CommonsException("user does not have a wallet", HttpStatus.NOT_FOUND));
        Wallet receiverWallet = walletRepository.findWalletByWalletId(transferRequestDto.getWalletId()).orElseThrow(() -> new CommonsException("wallet does not exist", HttpStatus.NOT_FOUND));
        TransferChargeDto transferCharge = transferChargeService.getCharge();
        if (wallet.getBalance().compareTo(transferRequestDto.getAmount().add(transferCharge.getCharge())) < 0) {
            throw new CommonsException("Insufficient Balance", HttpStatus.BAD_REQUEST);
        }
        if (!passwordEncoder.matches(transferRequestDto.getPin(), wallet.getPin())) {
            throw new CommonsException("Pin not correct", HttpStatus.BAD_REQUEST);
        }
        wallet.setBalance(wallet.getBalance().subtract(transferRequestDto.getAmount().add(transferCharge.getCharge())));
        receiverWallet.setBalance(receiverWallet.getBalance().add(transferRequestDto.getAmount()));

        wallet = walletRepository.save(wallet);
        receiverWallet = walletRepository.save(receiverWallet);

        // record transfer fee
        transferChargeTransactionService.recordTransferTransaction(wallet.getUserId(), receiverWallet.getUserId(), transferCharge.getCharge());

        Traveller sender = travellerRepository.findById(wallet.getUserId()).orElseThrow(() -> new CommonsException("user does not exist", HttpStatus.NOT_FOUND));
        Traveller receiver = travellerRepository.findById(receiverWallet.getUserId()).orElseThrow(() -> new CommonsException("user does not exist", HttpStatus.NOT_FOUND));

        // send transaction details to users
        CompletableFuture.runAsync(() -> {
            try {
                emailService.sendMail(sender.getEmail(), "DEBIT ALERT", "", "context/html");
                emailService.sendMail(receiver.getEmail(), "CREDIT ALERT", "", "context/html");

            } catch (IOException e) {
                log.error("an error occurred [{}] ", e.getMessage());
            }
        });

        log.info("transfer successful");
        return TransferResponse.builder()
                .walletId(transferRequestDto.getWalletId())
                .amount(transferRequestDto.getAmount())
                .message("Transfer Successful")
                .build();
    }
}
