package com.ibm.flight.repository;

import com.ibm.flight.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
	
	Airport findByCode(String code);

	boolean existsByCode(String code);
}
