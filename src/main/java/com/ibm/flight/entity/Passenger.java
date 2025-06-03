package com.ibm.flight.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "passengers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passenger_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "booking_id", nullable = false)
	private Booking booking;

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "passport_number", length = 50)
	private String passportNumber;

	@Column(name = "passport_expiry")
	private LocalDate passportExpiry;

	@Column(name = "frequent_flyer_number", length = 50)
	private String frequentFlyerNumber;

	@Column(name = "seat_assignment", length = 10)
	private String seatAssignment;

	@Column(name = "meal_preference", length = 50)
	private String mealPreference;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
}
