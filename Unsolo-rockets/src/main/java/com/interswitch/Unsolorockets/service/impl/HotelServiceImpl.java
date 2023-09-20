package com.interswitch.Unsolorockets.service.impl;

import com.interswitch.Unsolorockets.dtos.requests.HotelBookingDto;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;
import com.interswitch.Unsolorockets.models.HotelBooking;
import com.interswitch.Unsolorockets.models.Traveller;
import com.interswitch.Unsolorockets.respository.HotelRepository;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import com.interswitch.Unsolorockets.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {


    private final HotelRepository hotelRepository;

    private final TravellerRepository userRepository;


    @Override
    public HotelBooking bookHotel(Long userId, HotelBookingDto hotelBookingDto) throws UserNotFoundException {
        Traveller user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        HotelBooking booking = new HotelBooking();
        booking.setUser(user);
        booking.setHotel(hotelBookingDto.getHotel());
        booking.setGuestNumber(hotelBookingDto.getGuestNumber());
        booking.setCheckInDate(hotelBookingDto.getCheckInDate());
        booking.setCheckOutDate(hotelBookingDto.getCheckOutDate());
        booking.setRoomType(hotelBookingDto.getRoomType());

        hotelRepository.save(booking);

        return booking;
    }
}

