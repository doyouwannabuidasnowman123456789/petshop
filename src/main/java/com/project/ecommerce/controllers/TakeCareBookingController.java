package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dto.CreateTakeCareBookingRequestDTO;
import com.project.ecommerce.dto.SuccessResponseDTO;
import com.project.ecommerce.dto.TakeCareBookingDTO;
import com.project.ecommerce.exeptions.APIException;
import com.project.ecommerce.services.TakeCareBookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/take-care-bookings")
public class TakeCareBookingController {
    @Autowired
    private TakeCareBookingService takeCareBookingService;

    @PostMapping("")
    public ResponseEntity<?> createTakeCareBooking(
            @Valid @RequestBody CreateTakeCareBookingRequestDTO createTakeCareBookingRequestDTO) {
        if (!createTakeCareBookingRequestDTO.isEndDateAfterStartDate()) {
            throw new APIException("The end date must be after the start date");
        }

        if (!createTakeCareBookingRequestDTO.isLessOneMonth()) {
            throw new APIException("The booking must be less than one month");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        createTakeCareBookingRequestDTO.setEmail(authentication.getName());

        TakeCareBookingDTO takeCareBookingDTO = takeCareBookingService
                .createTakeCareBooking(createTakeCareBookingRequestDTO);
        return ResponseEntity.ok(takeCareBookingDTO);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTakeCareBookingsByEmail() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<TakeCareBookingDTO> takeCareBookingDTOs = takeCareBookingService
                .getAllTakeCareBookingsByEmail(authentication.getName());
        return ResponseEntity.ok(takeCareBookingDTOs);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBookings() {

        List<TakeCareBookingDTO> takeCareBookingDTOs = takeCareBookingService.getAllTakeCareBookings();
        return ResponseEntity.ok(takeCareBookingDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTakeCareBookingById(@PathVariable("id") Long id) {

        return ResponseEntity
                .ok(new SuccessResponseDTO("success", takeCareBookingService.deleteTakeCareBookingById(id)));
    }
}
