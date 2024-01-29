package com.project.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.TakeCareBooking;

public interface TakeCareBookingRepository extends JpaRepository<TakeCareBooking, Long>{
    List<TakeCareBooking> findAllByEmail(String email);
}
