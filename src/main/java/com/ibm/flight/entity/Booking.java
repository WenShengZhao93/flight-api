package com.ibm.flight.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;

	@Column(name = "booking_reference", nullable = false, length = 10, unique = true)
	private String bookingReference;

	@Column(name = "booking_date")
	private LocalDateTime bookingDate;

	@Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
	private BigDecimal totalAmount;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status", columnDefinition = "ENUM('pending', 'paid', 'refunded', 'cancelled') DEFAULT 'pending'")
	private PaymentStatus paymentStatus;

	@Column(name = "seat_preference", length = 255)
	private String seatPreference;

	@Column(name = "special_requests", columnDefinition = "TEXT")
	private String specialRequests;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public enum PaymentStatus {
		PENDING, PAID, REFUNDED, CANCELLED
	}
}