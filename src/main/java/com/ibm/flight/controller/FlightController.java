package com.ibm.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @PostMapping("/flights")
    public FlightSearchResponseDTO searchFlights(@RequestBody FlightSearchRequestDTO request) {
        return flightService.searchFlights(request);
    }
}
