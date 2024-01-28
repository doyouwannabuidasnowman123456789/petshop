package com.project.ecommerce.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationBookingDTO {
    private Long id;
    private String email;
    private Date date;
    private String description;
}
