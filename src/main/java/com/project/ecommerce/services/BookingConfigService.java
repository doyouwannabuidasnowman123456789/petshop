package com.project.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dto.UpdateBookingConfigDTO;
import com.project.ecommerce.entities.BookingConfig;
import com.project.ecommerce.exeptions.ResourceNotFoundException;
import com.project.ecommerce.repositories.BookingConfigRepository;

@Service
public class BookingConfigService {
    @Autowired
    private BookingConfigRepository bookingConfigRepository;

    public String updateBookingConfig(UpdateBookingConfigDTO updateBookingConfigDTO) {
        BookingConfig config = bookingConfigRepository.findById("SOKE")
            .orElseThrow(() -> new ResourceNotFoundException("BookingConfig", "id", "SOKE"));

            config.setMaxPlaceTakeCare(updateBookingConfigDTO.getMaxPlaceTakeCare());
			
			config.setMaxPlaceExamination(updateBookingConfigDTO.getMaxPlaceExamination());

            bookingConfigRepository.save(config);

            return "Successfully";
    }

    public BookingConfig getBookingConfig() {
        BookingConfig bookingConfig = bookingConfigRepository.findById("SOKE")
            .orElseThrow(() -> new ResourceNotFoundException("BookingConfig", "id", "SOKE"));

        return bookingConfig;
    }
}
