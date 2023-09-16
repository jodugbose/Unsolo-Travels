package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.WalletDto;
import com.interswitch.Unsolorockets.exceptions.CommonsException;

public interface WalletService {

    void createWallet(long userId) throws Exception;

    WalletDto getWallet(Long userId) throws CommonsException;

}
