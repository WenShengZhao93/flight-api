package com.ibm.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.flight.dto.FlightDetailResponseDTO;
import com.ibm.flight.dto.FlightSearchRequestDTO;
import com.ibm.flight.dto.FlightSearchResponseDTO;
import com.ibm.flight.service.FlightService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class FlightController {
    
	@Autowired
    private final FlightService flightService;
    
	/**
	 * query flight info by input query.
	 * 
	 * @param request flight's input query
	 * @return flights info
	 */
    @PostMapping("/flights")
    public FlightSearchResponseDTO searchFlights(@RequestBody FlightSearchRequestDTO request) {
        return flightService.searchFlights(request);
    }
    
	/**
	 * query flight info by flight's id.
	 * 
	 * @param flightId flight's id
	 * @return flight info
	 */
    @GetMapping("/flights/{flightId}")
    public ResponseEntity<FlightDetailResponseDTO> getFlightDetails(
        @PathVariable Long flightId) {
        
        FlightDetailResponseDTO details = flightService.getFlightDetails(flightId);
        return ResponseEntity.ok(details);
    }
}
