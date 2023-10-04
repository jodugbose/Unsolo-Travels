package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.exceptions.InvalidNinValidationException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;
import com.interswitch.Unsolorockets.service.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/kyc")
@RequiredArgsConstructor
public class KycController {
    private final KycService kycService;
    @PostMapping("/nin/{nin_id}")
    public ResponseEntity<Mono<?>> verifyNIN(@PathVariable(value = "nin_id") String ninId) throws InvalidNinValidationException,IllegalArgumentException, UserAlreadyExistException{
        var kyc = kycService.ninValidationRequest(ninId);
        return new ResponseEntity<>(kyc, HttpStatus.OK);
}
}
