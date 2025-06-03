package com.ibm.flight.service;

import com.ibm.flight.entity.Airline;
import com.ibm.flight.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineService {

	private final AirlineRepository airlineRepository;

	public AirlineService(AirlineRepository airlineRepository) {
		this.airlineRepository = airlineRepository;
	}

	public Airline createAirline(Airline airline) {
		return airlineRepository.save(airline);
	}

	public List<Airline> getAllAirlines() {
		return airlineRepository.findAll();
	}

	public Optional<Airline> getAirlineById(Integer id) {
		return airlineRepository.findById(id);
	}

	public Airline getAirlineByCode(String code) {
		return airlineRepository.findByCode(code);
	}

	public Airline updateAirline(Integer id, Airline updatedAirline) {
		return airlineRepository.findById(id).map(airline -> {
			airline.setName(updatedAirline.getName());
			airline.setCode(updatedAirline.getCode());
			airline.setLogoUrl(updatedAirline.getLogoUrl());
			airline.setContactNumber(updatedAirline.getContactNumber());
			airline.setWebsiteUrl(updatedAirline.getWebsiteUrl());
			return airlineRepository.save(airline);
		}).orElseGet(() -> {
			updatedAirline.setId(id);
			return airlineRepository.save(updatedAirline);
		});
	}

	public void deleteAirline(Integer id) {
		airlineRepository.deleteById(id);
	}
}