package com.project.ecommerce.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "take_care_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TakeCareBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email()
    private String email;
    private Date startDate;
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private EPetType petType;
    
    private Double price;
    private String note;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private Boolean status;
}
