package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.exceptions.CommonsException;
import com.interswitch.Unsolorockets.service.payment.PaymentRequestDto;
import com.interswitch.Unsolorockets.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping("/initiate/{userId}")
    public ResponseEntity<?> initiatePayment(@PathVariable Long userId, @RequestBody PaymentRequestDto paymentRequestDto) throws CommonsException {
        return new ResponseEntity<>(paymentService.initiatePayment(userId, paymentRequestDto), HttpStatus.OK);
    }
}
