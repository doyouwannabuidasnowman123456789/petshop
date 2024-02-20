package com.project.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingConfig {
    @Id
    private String id;
    private int maxPlaceTakeCare;
    private int currentTakeCareBooking;
    private int maxPlaceExamination;
    private int currentExaminationBooking;
    private double priceSlotSizeS;
    private double priceSlotSizeM;
    private double priceSlotSizeL;

    private String food1;
    private String food1Image;
    private double food1Price;
    private String food2;
    private String food2Image;
    private double food2Price;
    private String food3;
    private String food3Image;
    private double food3Price;

    private String service1;
    private double service1Price;
    private String service2;
    private double service2Price;
}
