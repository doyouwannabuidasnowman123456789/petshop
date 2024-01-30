package com.project.ecommerce.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "examination_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email()
    private String email;
    private Date date;
    private String description;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private Boolean status;
}
