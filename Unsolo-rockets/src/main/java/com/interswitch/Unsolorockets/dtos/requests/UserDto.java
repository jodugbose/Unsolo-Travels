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
    private String day;
    private String month;
    private String year;
    private String email;
    private String gender;
    private String role;
}
