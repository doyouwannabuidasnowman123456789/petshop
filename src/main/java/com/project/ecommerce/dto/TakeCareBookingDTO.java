package com.project.ecommerce.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeCareBookingDTO {
    private Long id;
    private String email;
    private Date startDate;
    private Date endDate;
    private String petType;
    private Double price;
    private String note;
    private Boolean status;
}
