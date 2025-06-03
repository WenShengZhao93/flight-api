package com.ibm.flight.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibm.flight.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    
    @Query("SELECT f FROM Flight f WHERE " +
            "f.departureAirport = :departureAirport AND " +
            "f.arrivalAirport = :arrivalAirport AND " +
            "f.departureTime BETWEEN :startDate AND :endDate AND " +
            "f.gateNumber >= :passengerCount")
     List<Flight> findAvailableFlights(
         @Param("departureAirport") String departureAirport,
         @Param("arrivalAirport") String arrivalAirport,
         @Param("startDate") LocalDateTime startDate,
         @Param("endDate") LocalDateTime endDate,
         @Param("passengerCount") Integer passengerCount
     );
    
}