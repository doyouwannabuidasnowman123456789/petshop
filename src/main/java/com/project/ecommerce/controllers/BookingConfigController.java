package com.project.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dto.SuccessResponseDTO;
import com.project.ecommerce.dto.UpdateBookingConfigDTO;
import com.project.ecommerce.services.BookingConfigService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/config")
public class BookingConfigController {
    @Autowired BookingConfigService bookingConfigService;

    @GetMapping("")
    public ResponseEntity<?> getBookingConfig() {
        return ResponseEntity.ok(bookingConfigService.getBookingConfig());
    }

    @PutMapping("")
    public ResponseEntity<?> updateBookingConfig(@Valid @RequestBody UpdateBookingConfigDTO updateBookingConfigDTO) {
        //TODO: process PUT request
        return ResponseEntity.ok(new SuccessResponseDTO("success", bookingConfigService.updateBookingConfig(updateBookingConfigDTO)));
    }
}
