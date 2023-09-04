package com.interswitch.Unsolorockets.dtos.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignUpResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
}
