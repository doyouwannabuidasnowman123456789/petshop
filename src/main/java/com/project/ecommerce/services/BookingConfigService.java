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
			config.setCurrentTakeCareBooking(updateBookingConfigDTO.getCurrentTakeCareBooking());
			config.setMaxPlaceExamination(updateBookingConfigDTO.getMaxPlaceExamination());
			config.setCurrentExaminationBooking(updateBookingConfigDTO.getCurrentExaminationBooking());

			config.setPriceSlotSizeS(updateBookingConfigDTO.getPriceSlotSizeS());
			config.setPriceSlotSizeM(updateBookingConfigDTO.getPriceSlotSizeM());
			config.setPriceSlotSizeL(updateBookingConfigDTO.getPriceSlotSizeL());

			config.setFood1(updateBookingConfigDTO.getFood1());
			config.setFood1Price(updateBookingConfigDTO.getFood1Price());

			config.setFood2(updateBookingConfigDTO.getFood2());
			config.setFood2Price(updateBookingConfigDTO.getFood2Price());

			config.setFood3(updateBookingConfigDTO.getFood3());
			config.setFood3Price(updateBookingConfigDTO.getFood3Price());

			config.setService1(updateBookingConfigDTO.getService1());
			config.setService1Price(updateBookingConfigDTO.getService1Price());
			config.setService2(updateBookingConfigDTO.getService2());
			config.setService2Price(updateBookingConfigDTO.getService2Price());

            bookingConfigRepository.save(config);

            return "Successfully";
    }

    public BookingConfig getBookingConfig() {
        BookingConfig bookingConfig = bookingConfigRepository.findById("SOKE")
            .orElseThrow(() -> new ResourceNotFoundException("BookingConfig", "id", "SOKE"));

        return bookingConfig;
    }
}
