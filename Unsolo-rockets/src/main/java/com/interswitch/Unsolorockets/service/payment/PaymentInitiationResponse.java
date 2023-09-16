package com.interswitch.Unsolorockets.service.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PaymentInitiationResponse {
    private String paymentLink;
    private BigDecimal amount;
    private String paymentReference;
    private PaymentCurrency currency;
    private PaymentStatus status;
    private String processorReference;
    private Processor processor;
}
