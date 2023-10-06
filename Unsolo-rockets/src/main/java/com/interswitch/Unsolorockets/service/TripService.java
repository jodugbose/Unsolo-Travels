package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.requests.DeleteRequest;
import com.interswitch.Unsolorockets.dtos.requests.TripRequest;
import com.interswitch.Unsolorockets.dtos.responses.TripResponse;
import com.interswitch.Unsolorockets.exceptions.TripNotFoundException;
import com.interswitch.Unsolorockets.exceptions.UserException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;
import com.interswitch.Unsolorockets.models.Trip;

import java.util.List;

public interface TripService {
    TripResponse createTrip(TripRequest request) throws UserException;
    TripResponse updateTripDetails(TripRequest request) throws UserException, TripNotFoundException;
    String deleteTrip(DeleteRequest request) throws TripNotFoundException, UserNotFoundException;
    List<String> findMatchingTravellers(TripRequest filterRequest);

    List<TripResponse> findTravellerTrips(long travellerId);
    List<TripResponse> findAllTrips();
}
