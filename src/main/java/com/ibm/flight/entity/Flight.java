package com.ibm.flight.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_id")
	private Integer id;

	@Column(name = "flight_number", nullable = false, length = 10)
	private String flightNumber;

	@ManyToOne
	@JoinColumn(name = "airline_id", nullable = false)
	private Airline airline;

	@ManyToOne
	@JoinColumn(name = "departure_airport_id", nullable = false)
	private Airport departureAirport;

	@ManyToOne
	@JoinColumn(name = "arrival_airport_id", nullable = false)
	private Airport arrivalAirport;

	@Column(name = "departure_time", nullable = false)
	private LocalDateTime departureTime;

	@Column(name = "arrival_time", nullable = false)
	private LocalDateTime arrivalTime;

	@Column(name = "duration_minutes", nullable = false)
	private Integer durationMinutes;

	@Column(name = "base_price_economy", nullable = false, precision = 10, scale = 2)
	private BigDecimal basePriceEconomy;

	@Column(name = "base_price_business", nullable = false, precision = 10, scale = 2)
	private BigDecimal basePriceBusiness;

	@Column(name = "base_price_first_class", nullable = false, precision = 10, scale = 2)
	private BigDecimal basePriceFirstClass;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "ENUM('scheduled', 'delayed', 'cancelled', 'departed', 'arrived')")
	private FlightStatus status;

	@Column(name = "gate_number", length = 10)
	private String gateNumber;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public enum FlightStatus {
		SCHEDULED, DELAYED, CANCELLED, DEPARTED, ARRIVED
	}
}