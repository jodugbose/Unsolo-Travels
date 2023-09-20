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
    private String departureLocation;

    private String arrivalLocation;

    private LocalDate departureDate;

    private LocalDate returnDate;

    @ManyToOne
    private Traveller user;

}
