package com.interswitch.Unsolorockets.dtos.requests;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserDto {
    private String firstName;
    private String lastName;
    private String password;
    private String password2;
    private String phoneNumber;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
    private String role;
}
