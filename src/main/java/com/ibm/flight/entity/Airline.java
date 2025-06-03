package com.ibm.flight.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "airlines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private Integer id;

    @Column(name = "airline_name", nullable = false, length = 100)
    private String name;

    @Column(name = "airline_code", nullable = false, length = 2, unique = true)
    private String code;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Column(name = "website_url", length = 255)
    private String websiteUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}