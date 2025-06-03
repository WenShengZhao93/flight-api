package com.ibm.flight.dto;

import java.util.List;

import com.ibm.flight.entity.Flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchResponseDTO {
    private List<Flight> departingFlights;
    private List<Flight> returningFlights;
    private boolean isRoundTrip;
}