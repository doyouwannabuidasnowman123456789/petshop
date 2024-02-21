package com.project.ecommerce.dto;

import com.project.ecommerce.entities.EOrderStatus;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDTO {
    @Email
    private String email;
    private EOrderStatus status;
}
