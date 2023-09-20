package com.interswitch.Unsolorockets.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@Entity
@Table(name = "hotelbooking")
@NoArgsConstructor
public class HotelBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hotel;
    private String roomType;
    private int guestNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    @ManyToOne
    private Traveller user;

}
