package com.project.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dto.CreateExaminationBookingRequestDTO;
import com.project.ecommerce.dto.ExaminationBookingDTO;
import com.project.ecommerce.entities.ExaminationBooking;
import com.project.ecommerce.exeptions.APIException;
import com.project.ecommerce.repositories.ExaminationBookingRepository;

@Service
public class ExaminationBookingService implements IExaminationBookingService{

    @Autowired
    private ExaminationBookingRepository examinationBookingRepository;

    @Autowired
	private ModelMapper modelMapper;

    @Override
    public ExaminationBookingDTO createExaminationBooking(CreateExaminationBookingRequestDTO examinationBookingRequestDTO) {
        ExaminationBooking examinationBooking = new ExaminationBooking();
        examinationBooking.setEmail(examinationBookingRequestDTO.getEmail());
        examinationBooking.setDate(examinationBookingRequestDTO.getDate());
        examinationBooking.setDescription(examinationBookingRequestDTO.getDescription());
        examinationBooking.setStatus(false);
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
        examinationBookingRepository.deleteById(id);
        return "Examination booking with id: " + id + " has been deleted";
    }

    @Override
    public List<ExaminationBookingDTO> getAllExaminationBooking() {
        List<ExaminationBooking> examinationBookings = examinationBookingRepository.findAll();

        List<ExaminationBookingDTO> examinationBookingDTOs = examinationBookings.stream().map(examinationBooking -> modelMapper.map(examinationBooking, ExaminationBookingDTO.class)).collect(Collectors.toList());
        return examinationBookingDTOs;
    }
}
