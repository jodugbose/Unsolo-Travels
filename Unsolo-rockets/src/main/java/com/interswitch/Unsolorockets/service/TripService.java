package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.requests.CreateTripRequest;
import com.interswitch.Unsolorockets.dtos.responses.CreateTripResponse;
import com.interswitch.Unsolorockets.exceptions.UserException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;

public interface TripService {
    CreateTripResponse createTrip(CreateTripRequest request) throws UserException;
}
