package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.requests.CreateTripRequest;
import com.interswitch.Unsolorockets.dtos.responses.CreateTripResponse;
import com.interswitch.Unsolorockets.exceptions.UserException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;
import com.interswitch.Unsolorockets.models.enums.JourneyType;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TripService {
    CreateTripResponse createTrip(CreateTripRequest request) throws UserException;

    List<String> findMatchingTravellers(
            String country, String aboutTheTrip, String journeyType,
            boolean splitCost, double budget, boolean firstTime
    );
}

