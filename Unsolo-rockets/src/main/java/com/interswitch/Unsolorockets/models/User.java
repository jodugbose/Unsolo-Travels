package com.interswitch.Unsolorockets.models;

import com.interswitch.Unsolorockets.models.enums.Gender;
import com.interswitch.Unsolorockets.models.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
//@Builder
//@AllArgsConstructor

public class User {


    private String firstName;
    private String lastName;
    @NonNull
    private String password;
    private String phoneNumber;
    @NonNull
    private String email;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Role role;


}
