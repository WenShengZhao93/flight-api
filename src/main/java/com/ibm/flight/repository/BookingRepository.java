package com.ibm.flight.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibm.flight.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
    // query upcoming books
    @Query(" SELECT b FROM Booking b WHERE b.user.username = :username "
    		+ " AND b.flight.departureTime > :now ")
    List<Booking> findUpcomingBookings(@Param("username") String username, @Param("now") LocalDateTime now);
    
    // query past books
    @Query(" SELECT b FROM Booking b WHERE b.user.username = :username "
    		+ " AND b.flight.departureTime <= :now ")
    List<Booking> findPastBookings(@Param("username") String username, @Param("now") LocalDateTime now);
    
    @Query(" SELECT b FROM Booking b WHERE b.user.username = :username "
    		+ " AND b.bookingReference = :bookingReference ")
    Optional<Booking> findByBookingReferenceAndUsername(@Param("bookingReference") String bookingReference,@Param("username") String username);
}