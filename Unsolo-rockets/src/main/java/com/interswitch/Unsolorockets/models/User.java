package com.interswitch.Unsolorockets.models;

import com.interswitch.Unsolorockets.models.enums.Gender;
import com.interswitch.Unsolorockets.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;
    @Column
    private Gender gender;

    private boolean isVerified;
    @Column
    private LocalDate dateOfBirth;

    @Column
    private Role role;
    private String validOTP;

    private String tokenForEmail;
}
