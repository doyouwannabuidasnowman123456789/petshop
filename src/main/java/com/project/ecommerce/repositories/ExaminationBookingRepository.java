package com.project.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.ExaminationBooking;

public interface ExaminationBookingRepository extends JpaRepository<ExaminationBooking, Long>{
    List<ExaminationBooking> findAllByEmail(String email);
}
