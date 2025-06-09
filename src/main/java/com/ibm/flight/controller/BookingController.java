package com.ibm.flight.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.flight.dto.BookingResponseDTO;
import com.ibm.flight.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

	@Autowired
	private final BookingService bookingService;

	/**
	 * qurey my book flight information.
	 * 
	 * @param user login's userinfo
	 * @return book flight information
	 */
	@GetMapping
	public ResponseEntity<Map<String, List<BookingResponseDTO>>> getUserBookings(
			@AuthenticationPrincipal UserDetails user) {

		Map<String, List<BookingResponseDTO>> bookings = bookingService.getUserBookings(user.getUsername());
		return ResponseEntity.ok(bookings);
	}

	/**
	 * qurey my book flight information by bookreference.
	 * 
	 * @param user login's userinfo
	 * @return book flight information
	 */
	@GetMapping("/{bookingReference}")
	public ResponseEntity<BookingResponseDTO> getBookingDetails(@PathVariable String bookingReference,
			@AuthenticationPrincipal UserDetails user) {

		BookingResponseDTO booking = bookingService.getBookingDetails(bookingReference, user.getUsername());
		return ResponseEntity.ok(booking);
	}
}
