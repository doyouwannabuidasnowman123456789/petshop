package com.project.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.BookingConfig;

public interface BookingConfigRepository extends JpaRepository<BookingConfig, String>{
}
