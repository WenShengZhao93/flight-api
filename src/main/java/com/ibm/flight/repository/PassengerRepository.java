package com.ibm.flight.repository;

import com.ibm.flight.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
	List<Passenger> findByBookingId(Integer bookingId);
}