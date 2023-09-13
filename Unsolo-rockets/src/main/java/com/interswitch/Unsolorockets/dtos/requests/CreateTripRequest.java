package com.interswitch.Unsolorockets.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class CreateTripRequest {

    private String travellerId;
    private String country;
    private String departureDay;
    private String departureMonth;
    private String departureYear;
    private String arrivalDay;
    private String arrivalMonth;
    private String arrivalYear;
    private String aboutTheTrip;
    private String journeyType;
    private boolean splitCost;
    private double budget;
    private boolean firstTime;
}
