package com.interswitch.Unsolorockets.service.impl;

import com.interswitch.Unsolorockets.dtos.requests.FlightBookingDto;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;
import com.interswitch.Unsolorockets.models.FlightBooking;
import com.interswitch.Unsolorockets.models.Traveller;
import com.interswitch.Unsolorockets.respository.FlightRepository;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import com.interswitch.Unsolorockets.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final TravellerRepository userRepository;

    @Override
    public FlightBooking bookFlight(Long userId, FlightBookingDto bookingDTO) throws UserNotFoundException {
        Traveller user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        FlightBooking booking = new FlightBooking();
        booking.setUser(user);
        booking.setDepartureLocation(bookingDTO.getDepartureLocation());
        booking.setArrivalLocation(bookingDTO.getArrivalLocation());
        booking.setDepartureDate(bookingDTO.getDepartureDate());
        booking.setReturnDate(bookingDTO.getReturnDate());

        flightRepository.save(booking);

        return booking;
    }

}





