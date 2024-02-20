package com.project.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dto.CreateExaminationBookingRequestDTO;
import com.project.ecommerce.dto.ExaminationBookingDTO;
import com.project.ecommerce.entities.BookingConfig;
import com.project.ecommerce.entities.ExaminationBooking;
import com.project.ecommerce.exeptions.APIException;
import com.project.ecommerce.exeptions.ResourceNotFoundException;
import com.project.ecommerce.repositories.BookingConfigRepository;
import com.project.ecommerce.repositories.ExaminationBookingRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExaminationBookingService implements IExaminationBookingService{

    @Autowired
    private ExaminationBookingRepository examinationBookingRepository;

    @Autowired
	private ModelMapper modelMapper;

    @Autowired
    private BookingConfigRepository bookingConfigRepository;

    @Override
    public ExaminationBookingDTO createExaminationBooking(CreateExaminationBookingRequestDTO examinationBookingRequestDTO) {
        BookingConfig config = bookingConfigRepository.findById("SOKE")
            .orElseThrow(() -> new ResourceNotFoundException("BookingConfig", "id", "SOKE"));

        if(config.getCurrentExaminationBooking() == config.getMaxPlaceExamination()) {
            throw new APIException("Run out of slot");
        }
        ExaminationBooking examinationBooking = new ExaminationBooking();
        examinationBooking.setEmail(examinationBookingRequestDTO.getEmail());
        examinationBooking.setDate(examinationBookingRequestDTO.getDate());
        examinationBooking.setDescription(examinationBookingRequestDTO.getDescription());
        examinationBooking.setStatus(false);

        // Minus 1 slot
        // Minus 1 slot in bookings config
        config.setCurrentExaminationBooking(config.getCurrentExaminationBooking() + 1);
        bookingConfigRepository.save(config);

        examinationBookingRepository.save(examinationBooking);

        ExaminationBookingDTO examinationBookingDTO = modelMapper.map(examinationBooking, ExaminationBookingDTO.class);
        return examinationBookingDTO;
    }

    @Override
    public List<ExaminationBookingDTO> getAllExaminationBookings(String email) {
        List<ExaminationBooking> examinationBookings = examinationBookingRepository.findAllByEmail(email);

        List<ExaminationBookingDTO> examinationBookingDTOs = examinationBookings.stream().map(examinationBooking -> modelMapper.map(examinationBooking, ExaminationBookingDTO.class))
				.collect(Collectors.toList());

        if (examinationBookingDTOs.size() == 0) {
            throw new APIException("No examination bookings found for this email: " + email);
        }
        return examinationBookingDTOs;
    }

    @Override
    public String deleteExaminationById(Long id) {
        ExaminationBooking examinationBooking = examinationBookingRepository.findById(id).orElseThrow(() -> new APIException("No examination booking found with id: " + id));
        BookingConfig config = bookingConfigRepository.findById("SOKE")
            .orElseThrow(() -> new ResourceNotFoundException("BookingConfig", "id", "SOKE"));

        config.setCurrentExaminationBooking(config.getCurrentExaminationBooking() - 1);
        bookingConfigRepository.save(config);
        
        examinationBookingRepository.delete(examinationBooking);;
        return "Examination booking with id: " + id + " has been deleted";
    }

    @Override
    public List<ExaminationBookingDTO> getAllExaminationBooking() {
        List<ExaminationBooking> examinationBookings = examinationBookingRepository.findAll();

        List<ExaminationBookingDTO> examinationBookingDTOs = examinationBookings.stream().map(examinationBooking -> modelMapper.map(examinationBooking, ExaminationBookingDTO.class)).collect(Collectors.toList());
        return examinationBookingDTOs;
    }
}
