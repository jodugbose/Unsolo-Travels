package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.requests.TripRequest;
import com.interswitch.Unsolorockets.dtos.responses.TripResponse;
import com.interswitch.Unsolorockets.exceptions.TripNotFoundException;
import com.interswitch.Unsolorockets.exceptions.UserException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;

public interface TripService {
    TripResponse createTrip(TripRequest request) throws UserException;
    TripResponse updateTripDetails(TripRequest request) throws UserException, TripNotFoundException;
    String deleteTrip(Long tripId, Long travellerId) throws TripNotFoundException, UserNotFoundException;

}
