package com.project.ecommerce.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dto.PaypalCompleteRequestDTO;
import com.project.ecommerce.dto.PaypalOrderBookingRequestDTO;
import com.project.ecommerce.dto.PaypalOrderRequestDTO;
import com.project.ecommerce.entities.CompletedOrder;
import com.project.ecommerce.entities.PaymentOrder;
import com.project.ecommerce.services.PaypalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/paypal")
@CrossOrigin(origins = "http://localhost:3000")
public class PaypalController {
    @Autowired
    private PaypalService paypalService;

    @PostMapping(value = "/init")
    public PaymentOrder createPayment(
            @Valid @RequestBody PaypalOrderRequestDTO paypalOrderRequestDTO) {
                System.out.println(paypalOrderRequestDTO.getAddress());
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // return paypalService.createPayment(new BigDecimal(paypalOrderRequestDTO.getTotal()));
        return paypalService.createPayment(paypalOrderRequestDTO, authentication.getName());
    }

    @PostMapping(value = "/capture")
    public CompletedOrder completePayment(@Valid @RequestBody PaypalCompleteRequestDTO paypalCompleteRequestDTO) {
        return paypalService.completePayment(paypalCompleteRequestDTO.getOrderID());
    }

    @PostMapping(value = "/booking/init")
    public PaymentOrder createPaymentForBooking(
            @Valid @RequestBody PaypalOrderBookingRequestDTO paypalOrderBookingRequestDTO) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                System.out.println("Here");
        // return paypalService.createPayment(new BigDecimal(paypalOrderRequestDTO.getTotal()));
        return paypalService.createPaymentForBooking(paypalOrderBookingRequestDTO, authentication.getName());
    }

    @PostMapping(value = "/booking/capture")
    public CompletedOrder completePaymentForBooking(@Valid @RequestBody PaypalCompleteRequestDTO paypalCompleteRequestDTO) {
        return paypalService.completePaymentForBooking(paypalCompleteRequestDTO.getOrderID());
    }
}
