package com.ibm.flight.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibm.flight.dto.BookingResponseDTO;
import com.ibm.flight.entity.Booking;
import com.ibm.flight.entity.Flight;
import com.ibm.flight.entity.User;
import com.ibm.flight.repository.BookingRepository;
import com.ibm.flight.repository.FlightRepository;
import com.ibm.flight.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
    
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
   
    public Map<String, List<BookingResponseDTO>> getUserBookings(String username) {
        LocalDateTime now = LocalDateTime.now();
        
        List<BookingResponseDTO> upcoming = bookingRepository
            .findUpcomingBookings(username, now)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        
        List<BookingResponseDTO> past = bookingRepository
            .findPastBookings(username, now)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        
        return Map.of(
            "upcoming", upcoming,
            "past", past
        );
    }
    
	public BookingResponseDTO getBookingDetails(String bookingReference, String username) {

		Optional<Booking> booking = bookingRepository.findByBookingReferenceAndUsername(bookingReference, username);

		return convertToDTO(booking.get());
	}
    
//    public BookingDTO createBooking(BookingRequestDTO request, String username) {
//        User user = userRepository.findByEmail(username)
//            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//        
//        Flight flight = flightRepository.findById(request.getFlightId())
//            .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
//        
//        if (flight.getAvailableSeats() < request.getPassengerCount()) {
//            throw new BusinessException("Not enough seats available");
//        }
//        
//        flight.setAvailableSeats(flight.getAvailableSeats() - request.getPassengerCount());
//        flightRepository.save(flight);
//        
//        Booking booking = new Booking();
//        booking.setBookingReference(generateBookingReference());
//        booking.setUser(user);
//        booking.setFlight(flight);
//        booking.setPassengerCount(request.getPassengerCount());
//        booking.setTotalPrice(flight.getPrice() * request.getPassengerCount());
//        booking.setBookingTime(LocalDateTime.now());
//        booking.setCoverImageUrl(flight.getCoverImageUrl()); // 设置封面图
//        
//        bookingRepository.save(booking);
//        
//        return convertToDTO(booking);
//    }
    
    private String generateBookingReference() {
        return "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private BookingResponseDTO convertToDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        BeanUtils.copyProperties(booking, dto);
        dto.setFlightNumber(booking.getFlight().getFlightNumber());
        dto.setDepartureAirport(booking.getFlight().getDepartureAirport());
        dto.setArrivalAirport(booking.getFlight().getArrivalAirport());
        dto.setDepartureTime(booking.getFlight().getDepartureTime());
        dto.setArrivalTime(booking.getFlight().getArrivalTime());
        dto.setCoverImageUrl(booking.getCoverImageUrl());
        dto.setPassengerCount(booking.getPassengerCount());
        dto.setTotalPrice(booking.getTotalPrice().doubleValue());
        dto.setBookingReference(booking.getBookingReference());
        return dto;
    }
}