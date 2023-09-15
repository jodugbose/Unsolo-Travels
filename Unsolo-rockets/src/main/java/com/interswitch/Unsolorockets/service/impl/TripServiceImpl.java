package com.interswitch.Unsolorockets.service.impl;

import com.interswitch.Unsolorockets.dtos.requests.CreateTripRequest;
import com.interswitch.Unsolorockets.dtos.responses.CreateTripResponse;
import com.interswitch.Unsolorockets.exceptions.UserException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;
import com.interswitch.Unsolorockets.models.Traveller;
import com.interswitch.Unsolorockets.models.Trip;
import com.interswitch.Unsolorockets.models.enums.JourneyType;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import com.interswitch.Unsolorockets.respository.TripRepository;
import com.interswitch.Unsolorockets.service.TripService;
import com.interswitch.Unsolorockets.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final TravellerRepository travellerRepository;
    private final AppUtils appUtils;


    @Override
    public CreateTripResponse createTrip(CreateTripRequest request) throws UserException {
        Optional<Traveller> optionalTraveller = travellerRepository.findById(Long.valueOf(request.getTravellerId()));
        if (optionalTraveller.isEmpty()) {
            throw new UserNotFoundException();
        }
        Traveller traveller = optionalTraveller.get();
        Trip trip = new Trip();
        BeanUtils.copyProperties(request, trip);
        LocalDate departureDate = appUtils.createLocalDate(request.getDepartureDate());
        LocalDate arrivalDate = appUtils.createLocalDate(request.getArrivalDate());
        trip.setDepartureDate(departureDate);
        trip.setArrivalDate(arrivalDate);
        trip.setTravellerId(traveller.getId());
        trip.setJourneyType(JourneyType.valueOf(request.getJourneyType().toUpperCase()));
        tripRepository.save(trip);
        CreateTripResponse tripResponse = new CreateTripResponse();
        BeanUtils.copyProperties(request, tripResponse);
        tripResponse.setTravellerName(traveller.getFirstName());
        return tripResponse;
    }


}
