package com.project.ecommerce.services;

import java.util.List;

import com.project.ecommerce.dto.CreateTakeCareBookingRequestDTO;
import com.project.ecommerce.dto.TakeCareBookingDTO;

public interface ITakeCareBookingService {
    List<TakeCareBookingDTO> getAllTakeCareBookingsByEmail(String email);
    TakeCareBookingDTO createTakeCareBooking(CreateTakeCareBookingRequestDTO createTakeCareBookingRequestDTO);
    String deleteTakeCareBookingById(Long id);
}
