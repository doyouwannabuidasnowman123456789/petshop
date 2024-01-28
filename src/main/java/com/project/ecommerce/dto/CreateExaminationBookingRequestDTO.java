package com.project.ecommerce.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExaminationBookingRequestDTO {
    @NotEmpty()
    @Email()
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Date must be in the future")
    private Date date;

    @NotEmpty()
    private String description;
}
