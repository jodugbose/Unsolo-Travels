package com.interswitch.Unsolorockets.service.payment;

import com.interswitch.Unsolorockets.respository.Transaction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Setter
@Getter
public class PaymentLogDto {

    private  String reference;

    private long userId;

    private  String source;

    private PaymentStatus status;

    private BigDecimal amount;

    private String transactionId;

    private PaymentCurrency paymentCurrency;

    public static PaymentLogDto fromPaymentLog(Transaction transaction) {
        PaymentLogDto dto = new PaymentLogDto();
        BeanUtils.copyProperties(transaction, dto);
        dto.setUserId(transaction.getUserId());
        dto.setTransactionId(transaction.getTransactionReference());
        return  dto;
    }
}
