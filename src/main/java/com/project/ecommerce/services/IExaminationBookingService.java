package com.project.ecommerce.services;

import java.util.List;

import com.project.ecommerce.dto.CreateExaminationBookingRequestDTO;
import com.project.ecommerce.dto.ExaminationBookingDTO;

public interface IExaminationBookingService {
    ExaminationBookingDTO createExaminationBooking(CreateExaminationBookingRequestDTO examinationBookingRequestDTO);
    List<ExaminationBookingDTO> getAllExaminationBookings(String email);
    String deleteExaminationById(Long id);
    List<ExaminationBookingDTO> getAllExaminationBooking();
}
