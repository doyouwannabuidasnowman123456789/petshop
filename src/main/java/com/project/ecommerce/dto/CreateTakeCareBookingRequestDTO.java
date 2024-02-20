package com.project.ecommerce.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.ecommerce.entities.EPetType;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTakeCareBookingRequestDTO {
    private String email;

    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotNull(message = "Pet type is required")
    private EPetType petType;

    private String note;

    @NotNull
    @Min(value = 0, message = "Total price must be greater than 0")
    private double totalPrice;

    public long getNumberOfDays() {
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(localStartDate, localEndDate);
    }

    public boolean isEndDateAfterStartDate() {
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localEndDate.isAfter(localStartDate);
    }

    public boolean isLessOneMonth() {
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localEndDate.isBefore(localStartDate.plusMonths(1));
    }
}
