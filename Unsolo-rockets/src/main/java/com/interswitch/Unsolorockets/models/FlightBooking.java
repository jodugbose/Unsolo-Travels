package com.interswitch.Unsolorockets.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@Entity
@Table(name = "flightbooking")
@NoArgsConstructor
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String departureLocation;
    @Column(nullable = false)
    private String arrivalLocation;

    @Column(nullable = false)
    private LocalDate departureDate;
    @Column(nullable = false)
    private LocalDate arrivalDate;
    @ManyToOne
//    @Column(nullable = false)
    private Traveller user;

}
