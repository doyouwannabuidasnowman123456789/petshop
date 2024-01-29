package com.project.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordRequestDTO {
    @NotEmpty(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
}
