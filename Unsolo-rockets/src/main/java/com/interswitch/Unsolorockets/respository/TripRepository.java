package com.interswitch.Unsolorockets.respository;

import com.interswitch.Unsolorockets.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findTripsByTravellerId(long travellerId);
}

