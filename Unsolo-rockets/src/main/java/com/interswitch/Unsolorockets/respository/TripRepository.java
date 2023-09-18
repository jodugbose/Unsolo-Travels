package com.interswitch.Unsolorockets.respository;

import com.interswitch.Unsolorockets.models.Trip;
import com.interswitch.Unsolorockets.models.enums.JourneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
  /*
    @Query("SELECT t FROM Trip t " +
            "WHERE " +
            "(:country IS NULL OR t.country = :country) AND " +
            "(:aboutTheTrip IS NULL OR t.aboutTheTrip = :aboutTheTrip) AND " +
            "(:journeyType IS NULL OR t.journeyType = :journeyType) AND " +
            "(:splitCost IS NULL OR t.splitCost = :splitCost) AND " +
            "(:budget IS NULL OR t.budget = :budget) AND " +
            "(:firstTime IS NULL OR t.firstTime = :firstTime)")
    List<Trip> findMatchingTrips(
            @Param("country") String country,
            @Param("aboutTheTrip") String aboutTheTrip,
            @Param("journeyType") JourneyType journeyType,
            @Param("splitCost") boolean splitCost,
            @Param("budget") double budget,
            @Param("firstTime") boolean firstTime
    );

}

   */
}
