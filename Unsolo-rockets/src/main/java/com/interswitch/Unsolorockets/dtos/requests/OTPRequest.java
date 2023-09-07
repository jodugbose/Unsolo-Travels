package com.interswitch.Unsolorockets.dtos.requests;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class OTPRequest {
//    @NonNull(message = "Enter 5-digit OTP sent to your phone")
//    @Size(min = 5, max = 5)
//    private String otp;
    private String emailForOTP;
}
