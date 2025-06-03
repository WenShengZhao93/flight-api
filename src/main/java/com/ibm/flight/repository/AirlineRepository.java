package com.ibm.flight.repository;

import com.ibm.flight.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {

	Airline findByCode(String code);

	boolean existsByCode(String code);

	Airline findByNameIgnoreCase(String name);
}