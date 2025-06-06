package com.ibm.flight.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibm.flight.dto.FlightDetailResponseDTO;
import com.ibm.flight.dto.FlightSearchRequestDTO;
import com.ibm.flight.dto.FlightSearchResponseDTO;
import com.ibm.flight.entity.Flight;
import com.ibm.flight.repository.FlightRepository;

import lombok.RequiredArgsConstructor;

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
    
    public FlightDetailResponseDTO getFlightDetails(Long flightId) {
    	Optional<Flight> flight = flightRepository.findById(flightId);
        
        if (flight.isEmpty()) {
        	throw new RuntimeException("Flight not found");
        }
        
        return convertToDetailDTO(flight.get());
    }
    
    private FlightDetailResponseDTO convertToDetailDTO(Flight flight) {
    	FlightDetailResponseDTO flightDetailDTO = new FlightDetailResponseDTO();
        BeanUtils.copyProperties(flight, flightDetailDTO);
        flightDetailDTO.setPrice(flight.getBasePriceBusiness().doubleValue());
        flightDetailDTO.setId(flight.getId().longValue());
        flightDetailDTO.setAvailableSeats(Integer.valueOf(flight.getGateNumber()));
           
        return flightDetailDTO;
    }
}
