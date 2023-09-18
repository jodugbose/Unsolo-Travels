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
import java.util.*;
import java.util.stream.Collectors;

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
        LocalDate departureDate = appUtils.createLocalDate(request.getDepartureDay(), request.getDepartureMonth(), request.getDepartureYear());
        LocalDate arrivalDate = appUtils.createLocalDate(request.getArrivalDay(), request.getArrivalMonth(), request.getArrivalYear());
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

    @Override
    public List<String> findMatchingTravellers(String country, String aboutTheTrip, String journeyType, boolean splitCost, double budget, boolean firstTime) {
        List<Trip> trips = tripRepository.findAll(); // Assuming you want to retrieve all trips from the repository

        List<Trip> matchingTrips = new ArrayList<>();

        // Step 1: Filter by country
        for (Trip trip : trips) {
            if (trip.getCountry().equalsIgnoreCase(country)) {
                matchingTrips.add(trip);
            }
        }

        // Check if the country matched
        if (matchingTrips.isEmpty()) {
            return Collections.emptyList(); // No further matching is done if the country doesn't match
        }

        // Now, you can proceed with additional filtering based on other criteria
        List<Trip> finalMatchingTrips = new ArrayList<>();
        for (Trip trip : matchingTrips) {
            int matchingFieldCount = 0;

            // Check if fields match and increment the count for each match
            if (aboutTheTrip != null && aboutTheTrip.equalsIgnoreCase(trip.getAboutTheTrip())) {
                matchingFieldCount++;
            }

            if (journeyType != null && journeyType.equalsIgnoreCase(trip.getJourneyType().toString())) {
                matchingFieldCount++;
            }

            if (!splitCost || splitCost == trip.isSplitCost()) {
                matchingFieldCount++;
            }

            if (budget == trip.getBudget()) {
                matchingFieldCount++;
            }

            if (firstTime == trip.isFirstTime()) {
                matchingFieldCount++;
            }

            // Ensure at least four fields match (including country)
            if (matchingFieldCount >= 4) {
                finalMatchingTrips.add(trip);
            }
        }

        // Step 3: Map to traveler names
        List<String> matchingTravellers = finalMatchingTrips.stream()
                .map(trip -> getTravellerName(trip.getTravellerId()))
                .collect(Collectors.toList());

        return matchingTravellers;
    }

    private String getTravellerName(Long travelerId) {
        Optional<Traveller> optionalTraveller = travellerRepository.findById(travelerId);
        if (optionalTraveller.isPresent()) {
            Traveller traveller = optionalTraveller.get();

            return traveller.getFirstName() + " " + traveller.getLastName();
        }
        return ""; // Return an empty string if the traveler is not found
    }
}

