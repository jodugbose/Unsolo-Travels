package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.TransferResponse;
import com.interswitch.Unsolorockets.dtos.requests.CreateWalletRequest;
import com.interswitch.Unsolorockets.dtos.requests.TransferRequestDto;
import com.interswitch.Unsolorockets.dtos.responses.WalletDto;
import com.interswitch.Unsolorockets.exceptions.CommonsException;

import java.io.IOException;

public interface WalletService {

    void createWallet(long userId, CreateWalletRequest createWalletRequest) throws Exception;

    WalletDto getWallet(Long userId) throws CommonsException;

    TransferResponse transfer(Long userId, TransferRequestDto transferRequestDto) throws CommonsException, IOException;
}
