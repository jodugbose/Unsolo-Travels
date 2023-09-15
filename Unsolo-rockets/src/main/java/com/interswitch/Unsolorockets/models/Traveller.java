package com.interswitch.Unsolorockets.models;


import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
//@Entity
//@Table(name = "travellers")
public class Traveller extends User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private LocalDate dateJoined;


}
