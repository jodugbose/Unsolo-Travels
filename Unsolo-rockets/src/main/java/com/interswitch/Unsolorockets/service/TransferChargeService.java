package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.TransferChargeDto;
import com.interswitch.Unsolorockets.dtos.UpdateTransferChargeDto;

public interface TransferChargeService {

    TransferChargeDto updateCharge(long userId, UpdateTransferChargeDto updateTransferChargeDto);

    TransferChargeDto getCharge();
}
