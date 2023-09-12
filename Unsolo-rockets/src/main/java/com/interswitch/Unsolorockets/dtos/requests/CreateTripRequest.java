package com.interswitch.Unsolorockets.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class CreateTripRequest {

    private long travellerId;
    private String country;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String aboutTheTrip;
    private String journeyType;
    private boolean splitCost;
    private double budget;
    private boolean firstTime;
}
