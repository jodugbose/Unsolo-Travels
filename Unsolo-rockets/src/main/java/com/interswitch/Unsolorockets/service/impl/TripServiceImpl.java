package com.interswitch.Unsolorockets.service.impl;

import com.interswitch.Unsolorockets.dtos.requests.TripRequest;
import com.interswitch.Unsolorockets.dtos.responses.TripResponse;
import com.interswitch.Unsolorockets.dtos.responses.UserProfileResponse;
import com.interswitch.Unsolorockets.exceptions.TripNotFoundException;
import com.interswitch.Unsolorockets.exceptions.UserException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;
import com.interswitch.Unsolorockets.models.Admin;
import com.interswitch.Unsolorockets.models.Traveller;
import com.interswitch.Unsolorockets.models.Trip;
import com.interswitch.Unsolorockets.models.User;
import com.interswitch.Unsolorockets.models.enums.Gender;
import com.interswitch.Unsolorockets.models.enums.JourneyType;
import com.interswitch.Unsolorockets.models.enums.Role;
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
    public TripResponse createTrip(TripRequest request) throws UserException {
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
        TripResponse tripResponse = new TripResponse();
        BeanUtils.copyProperties(request, tripResponse);
        tripResponse.setTravellerName(traveller.getFirstName());
        return tripResponse;
    }

    @Override
    public TripResponse updateTripDetails(TripRequest request) throws UserException, TripNotFoundException {
        Optional <Traveller> userOptional = travellerRepository.findById(Long.valueOf(request.getTravellerId()));
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }
        Traveller traveller = userOptional.get();

        Optional<Trip> tripOptional = tripRepository.findById(request.getTripId());
        if(tripOptional.isEmpty()){
            throw new TripNotFoundException("Trip not found");
        }
        Trip trip = tripOptional.get();

        LocalDate arrivalDate = appUtils.createLocalDate(request.getArrivalDate());
        LocalDate departureDate = appUtils.createLocalDate(request.getDepartureDate());

        if (request.getAboutTheTrip() != null) {
            trip.setAboutTheTrip(request.getAboutTheTrip());
        }

        if (request.getDepartureDate() != null) {
            trip.setDepartureDate(departureDate);
        }

        if (request.getArrivalDate() != null) {
            trip.setArrivalDate(arrivalDate);
        }

        if (request.getBudget() > 0.00) {
            trip.setBudget(request.getBudget());
        }

        if (request.isFirstTime() != trip.isFirstTime()) {
            trip.setFirstTime(request.isFirstTime());
        }
        if (request.isSplitCost() != trip.isSplitCost()){
            trip.setSplitCost(request.isSplitCost());
        }
        if (request.getCountry() != null){
            trip.setCountry(request.getCountry());
        }
        if (request.getJourneyType() != null){
            trip.setJourneyType(JourneyType.valueOf(request.getJourneyType().toUpperCase()));
        }

        tripRepository.save(trip);

        TripResponse tripResponse = new TripResponse();
        BeanUtils.copyProperties(request, tripResponse);
        tripResponse.setTravellerName(traveller.getFirstName());
        tripResponse.setArrivalDate(arrivalDate);
        tripResponse.setDepartureDate(departureDate);
        return tripResponse;
    }


}
