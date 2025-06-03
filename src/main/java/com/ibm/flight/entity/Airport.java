package com.ibm.flight.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "airports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "airport_id")
	private Integer id;

	@Column(name = "airport_code", nullable = false, length = 3, unique = true)
	private String code;

	@Column(name = "airport_name", nullable = false, length = 100)
	private String name;

	@Column(name = "city", nullable = false, length = 50)
	private String city;

	@Column(name = "country", nullable = false, length = 50)
	private String country;

	@Column(name = "latitude", precision = 10, scale = 8)
	private BigDecimal latitude;

	@Column(name = "longitude", precision = 11, scale = 8)
	private BigDecimal longitude;

	@Column(name = "timezone", length = 50)
	private String timezone;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
