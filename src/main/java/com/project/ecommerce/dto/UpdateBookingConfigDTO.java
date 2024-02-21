package com.project.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingConfigDTO {
    private int maxPlaceTakeCare;
    private int maxPlaceExamination;
}
