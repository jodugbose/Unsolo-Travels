package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.dtos.requests.CreateTripRequest;
import com.interswitch.Unsolorockets.exceptions.UserException;
import com.interswitch.Unsolorockets.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/trip")
public class TripController {
    private final TripService tripService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateTripRequest request) throws UserException {
        var response = tripService.createTrip(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/match")
    public ResponseEntity<List<String>> findMatchingTravellers(
            @RequestParam("country") String country,
            @RequestParam("aboutTheTrip") String aboutTheTrip,
            @RequestParam("journeyType") String journeyType,
            @RequestParam("splitCost") boolean splitCost,
            @RequestParam("budget") double budget,
            @RequestParam("firstTime") boolean firstTime) {
        List<String> matchingTravellers = tripService.findMatchingTravellers(
                country, aboutTheTrip, journeyType, splitCost, budget, firstTime);
        return new ResponseEntity<>(matchingTravellers, HttpStatus.OK);
    }
}
