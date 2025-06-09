package com.ibm.flight.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
	
    private String flightNumber;
    private String departureAirport;
    private String bookingReference;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double totalPrice;
    private Integer passengerCount;
    private String coverImageUrl;
}
