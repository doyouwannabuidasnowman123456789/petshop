package com.project.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderItemDTO {
    @NotNull
    private Long productId;

    @NotNull
    @Min(value = 1, message = "Quantity must be greater than 1")
    private Integer quantity;

    @NotNull
    @Min(value = 0, message = "Quantity must be greater than 0")
    private double orderedProductPrice;
}
