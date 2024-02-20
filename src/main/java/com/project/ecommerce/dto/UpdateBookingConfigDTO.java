package com.project.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingConfigDTO {
    private int maxPlaceTakeCare;
    private int currentTakeCareBooking;
    private int maxPlaceExamination;
    private int currentExaminationBooking;
    private double priceSlotSizeS;
    private double priceSlotSizeM;
    private double priceSlotSizeL;

    private String food1;
    private double food1Price;
    private String food2;
    private double food2Price;
    private String food3;
    private double food3Price;

    private String service1;
    private double service1Price;
    private String service2;
    private double service2Price;
}
