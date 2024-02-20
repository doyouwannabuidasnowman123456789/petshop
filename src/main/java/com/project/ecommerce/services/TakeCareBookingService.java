package com.project.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dto.CreateTakeCareBookingRequestDTO;
import com.project.ecommerce.dto.TakeCareBookingDTO;
import com.project.ecommerce.entities.BookingConfig;
import com.project.ecommerce.entities.TakeCareBooking;
import com.project.ecommerce.exeptions.APIException;
import com.project.ecommerce.exeptions.ResourceNotFoundException;
import com.project.ecommerce.repositories.BookingConfigRepository;
import com.project.ecommerce.repositories.TakeCareBookingRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TakeCareBookingService implements ITakeCareBookingService{
    @Autowired
    private TakeCareBookingRepository takeCareBookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingConfigRepository bookingConfigRepository;

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
        BookingConfig config = bookingConfigRepository.findById("SOKE")
            .orElseThrow(() -> new ResourceNotFoundException("BookingConfig", "id", "SOKE"));

            if(config.getCurrentTakeCareBooking() == config.getMaxPlaceTakeCare()) {
                throw new APIException("Run out of slot");
            }
        TakeCareBooking takeCareBooking = new TakeCareBooking();
        takeCareBooking.setEmail(createTakeCareBookingRequestDTO.getEmail());
        takeCareBooking.setStartDate(createTakeCareBookingRequestDTO.getStartDate());
        takeCareBooking.setEndDate(createTakeCareBookingRequestDTO.getEndDate());
        takeCareBooking.setPetType(createTakeCareBookingRequestDTO.getPetType());

        long numberDay = createTakeCareBookingRequestDTO.getNumberOfDays();
        takeCareBooking.setPrice(createTakeCareBookingRequestDTO.getTotalPrice() * numberDay);
        takeCareBooking.setNote(createTakeCareBookingRequestDTO.getNote());
        takeCareBooking.setStatus(false);

        // Minus 1 slot in bookings config
        
        config.setCurrentTakeCareBooking(config.getCurrentTakeCareBooking() + 1);
        bookingConfigRepository.save(config);
        takeCareBookingRepository.save(takeCareBooking);

        TakeCareBookingDTO takeCareBookingDTO = modelMapper.map(takeCareBooking, TakeCareBookingDTO.class);
        return takeCareBookingDTO;
    }

    @Override
    public String deleteTakeCareBookingById(Long id) {
        TakeCareBooking takeCareBooking = takeCareBookingRepository.findById(id).orElseThrow(() -> new APIException("No take care booking found for this id: " + id));

        BookingConfig config = bookingConfigRepository.findById("SOKE")
            .orElseThrow(() -> new ResourceNotFoundException("BookingConfig", "id", "SOKE"));

        config.setCurrentTakeCareBooking(config.getCurrentTakeCareBooking() - 1);
        bookingConfigRepository.save(config);
        takeCareBookingRepository.delete(takeCareBooking);
        return "Take care booking " + id + " deleted successfully";
    }

    @Override
    public List<TakeCareBookingDTO> getAllTakeCareBookings() {
        List<TakeCareBooking> takeCareBookings = takeCareBookingRepository.findAll();

        List<TakeCareBookingDTO> takeCareBookingDTOs = takeCareBookings.stream().map(takeCareBooking -> modelMapper.map(takeCareBooking, TakeCareBookingDTO.class)).collect(Collectors.toList());
        return takeCareBookingDTOs;
    }
}
