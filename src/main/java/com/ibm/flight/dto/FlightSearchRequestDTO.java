package com.ibm.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchRequestDTO {
	private String departureAirport;
	private String arrivalAirport;
	private LocalDate departureDate;
	private LocalDate returnDate;
	private Integer passengerCount;
	private boolean isRoundTrip;
}