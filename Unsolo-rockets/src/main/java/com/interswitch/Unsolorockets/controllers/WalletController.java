package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.dtos.responses.ApiResponse;
import com.interswitch.Unsolorockets.exceptions.CommonsException;
import com.interswitch.Unsolorockets.service.WalletService;
import com.interswitch.Unsolorockets.service.payment.PaymentRequestDto;
import com.interswitch.Unsolorockets.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {
//    private final WalletService walletService;

    private final PaymentService paymentService;

//    @GetMapping("/view_balance")
//    public ResponseEntity<ApiResponse<ViewWalletResponseDto>> viewBalance(){
//        return ResponseEntity.ok(walletService.viewBalance());
//    }
//
//    @PostMapping("/transaction-webhook")
//    public ResponseEntity<String> processWebhookEvent(@RequestBody WebHookResponse<VerifyTransaction> webHookResponse) {
//        return walletService.processWebHookEvent(webHookResponse);
//    }
//    @PostMapping("/validate-account")
//    public ResponseEntity<ApiResponse> validateAccount(@RequestBody AccountValidationDto accountValidationDto) {
//        return ResponseEntity.ok().body(walletService.validateAccount(accountValidationDto));
//    }
//    @PatchMapping("/updateWalletPin")
//    public ResponseEntity<ApiResponse> updateWalletPin(@Valid @RequestBody CreateTransactionPinDto transactionPinDto){
//        return walletService.updateWalletPin(transactionPinDto);
//    }

    @PostMapping("/payment/{userId}")
    public ResponseEntity<?> initiatePayment(@PathVariable Long userId, @RequestBody PaymentRequestDto paymentRequestDto) throws CommonsException {
        return new ResponseEntity<>(paymentService.initiatePayment(userId, paymentRequestDto), HttpStatus.OK);
    }
}