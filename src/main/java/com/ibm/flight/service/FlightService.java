package com.ibm.flight.service;

import com.ibm.flight.dto.FlightSearchRequestDTO;
import com.ibm.flight.dto.FlightSearchResponseDTO;
import com.ibm.flight.entity.Flight;
import com.ibm.flight.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    
    private final FlightRepository flightRepository;
    
    public FlightSearchResponseDTO searchFlights(FlightSearchRequestDTO request) {
        // process outbound flight
        LocalDateTime departureStart = request.getDepartureDate().atStartOfDay();
        LocalDateTime departureEnd = departureStart.plusDays(1).minusSeconds(1);
        
        List<Flight> departingFlights = flightRepository.findAvailableFlights(
            request.getDepartureAirport(),
            request.getArrivalAirport(),
            departureStart,
            departureEnd,
            request.getPassengerCount()
        );
        
        // process return flight
        List<Flight> returningFlights = null;
        if (request.isRoundTrip() && request.getReturnDate() != null) {
            LocalDateTime returnStart = request.getReturnDate().atStartOfDay();
            LocalDateTime returnEnd = returnStart.plusDays(1).minusSeconds(1);
            
            returningFlights = flightRepository.findAvailableFlights(
                request.getArrivalAirport(),
                request.getDepartureAirport(),
                returnStart,
                returnEnd,
                request.getPassengerCount()
            );
        }
        
        return FlightSearchResponseDTO.builder()
            .departingFlights(departingFlights)
            .returningFlights(returningFlights)
            .isRoundTrip(request.isRoundTrip())
            .build();
    }
}
