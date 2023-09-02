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
