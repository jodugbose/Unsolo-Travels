package com.interswitch.Unsolorockets.respository;

import com.interswitch.Unsolorockets.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
