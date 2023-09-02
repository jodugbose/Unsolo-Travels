package com.interswitch.Unsolorockets.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class SignUpResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
}
