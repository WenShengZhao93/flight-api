package com.ibm.flight.repository;

import com.ibm.flight.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	Booking findByBookingReference(String bookingReference);

	List<Booking> findByUserId(Integer userId);

	List<Booking> findByFlightId(Integer flightId);
}