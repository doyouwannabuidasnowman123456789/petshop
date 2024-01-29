package com.project.ecommerce.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dto.CreateTakeCareBookingRequestDTO;
import com.project.ecommerce.dto.TakeCareBookingDTO;
import com.project.ecommerce.entities.TakeCareBooking;
import com.project.ecommerce.exeptions.APIException;
import com.project.ecommerce.repositories.TakeCareBookingRepository;

@Service
public class TakeCareBookingService implements ITakeCareBookingService{
    @Autowired
    private TakeCareBookingRepository takeCareBookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TakeCareBookingDTO> getAllTakeCareBookingsByEmail(String email) {
        List<TakeCareBooking> takeCareBookings = takeCareBookingRepository.findAllByEmail(email);
        List<TakeCareBookingDTO> takeCareBookingDTOs = takeCareBookings.stream().map(takeCareBooking -> modelMapper.map(takeCareBooking, TakeCareBookingDTO.class))
				.collect(Collectors.toList());

        if (takeCareBookingDTOs.size() == 0) {
            throw new APIException("No take care bookings found for this email: " + email);
        }

        return takeCareBookingDTOs;
    }

    @Override
    public TakeCareBookingDTO createTakeCareBooking(CreateTakeCareBookingRequestDTO createTakeCareBookingRequestDTO) {
        TakeCareBooking takeCareBooking = new TakeCareBooking();
        takeCareBooking.setEmail(createTakeCareBookingRequestDTO.getEmail());
        takeCareBooking.setStartDate(createTakeCareBookingRequestDTO.getStartDate());
        takeCareBooking.setEndDate(createTakeCareBookingRequestDTO.getEndDate());
        takeCareBooking.setPetType(createTakeCareBookingRequestDTO.getPetType());
        Double price = 0.0;
        if (createTakeCareBookingRequestDTO.getPetType().toString().equals("SENIOR_DOG")) {
            System.out.println("SENIOR_DOG");
            price = 10.0;
        } else {
            price = 5.0;
        }

        long numberDay = createTakeCareBookingRequestDTO.getNumberOfDays();
        takeCareBooking.setPrice(price * numberDay);
        takeCareBooking.setNote(createTakeCareBookingRequestDTO.getNote());

        takeCareBookingRepository.save(takeCareBooking);

        TakeCareBookingDTO takeCareBookingDTO = modelMapper.map(takeCareBooking, TakeCareBookingDTO.class);
        return takeCareBookingDTO;
    }

    @Override
    public String deleteTakeCareBookingById(Long id) {
        TakeCareBooking takeCareBooking = takeCareBookingRepository.findById(id).orElseThrow(() -> new APIException("No take care booking found for this id: " + id));

        takeCareBookingRepository.delete(takeCareBooking);
        return "Take care booking " + id + " deleted successfully";
    }
}
