package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dto.CreateExaminationBookingRequestDTO;
import com.project.ecommerce.dto.ExaminationBookingDTO;
import com.project.ecommerce.dto.SuccessResponseDTO;
import com.project.ecommerce.services.ExaminationBookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/examination-bookings")
public class ExaminationBookingController {
    @Autowired
    private ExaminationBookingService examinationBookingService;

    @PostMapping("")
    public ResponseEntity<ExaminationBookingDTO> createExaminationBooking(@Valid @RequestBody CreateExaminationBookingRequestDTO examinationBookingRequestDTO) {
        ExaminationBookingDTO examinationBookingDTO = examinationBookingService.createExaminationBooking(examinationBookingRequestDTO);
        return ResponseEntity.ok(examinationBookingDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExaminationBookings() {
        List<ExaminationBookingDTO> examinationBookingDTOs = examinationBookingService.getAllExaminationBooking();
        return ResponseEntity.ok(examinationBookingDTOs);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllExaminationBookingsByEmail(@RequestParam(name = "email", required = true) String email) {
        List<ExaminationBookingDTO> examinationBookingDTOs = examinationBookingService.getAllExaminationBookings(email);
        return ResponseEntity.ok(examinationBookingDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExaminationById(
        @PathVariable("id") Long id
    ) {

        return ResponseEntity.ok(new SuccessResponseDTO(
            "success",
            examinationBookingService.deleteExaminationById(id)
        ));
    }
}
