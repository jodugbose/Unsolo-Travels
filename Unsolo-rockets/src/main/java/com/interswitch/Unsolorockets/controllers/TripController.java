package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.dtos.requests.DeleteRequest;
import com.interswitch.Unsolorockets.dtos.requests.TripRequest;
import com.interswitch.Unsolorockets.exceptions.TripNotFoundException;
import com.interswitch.Unsolorockets.exceptions.UserException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;
import com.interswitch.Unsolorockets.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/trip")
public class TripController {
    private final TripService tripService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TripRequest request) throws UserException {
        var response = tripService.createTrip(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/")
    public ResponseEntity<?> updateTrip(@RequestBody TripRequest request) throws UserException, TripNotFoundException {
        var response = tripService.updateTripDetails(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity <?> deleteTrip(@RequestBody DeleteRequest request) throws UserNotFoundException, TripNotFoundException {
        var response = tripService.deleteTrip(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{travellerId}")
    public ResponseEntity<?> viewTravellerTrips(@PathVariable long travellerId){
        var response = tripService.findTravellerTrips(travellerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> viewAllTrips(){
        var response = tripService.findAllTrips();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
