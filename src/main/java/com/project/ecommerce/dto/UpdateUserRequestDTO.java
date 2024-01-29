package com.project.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDTO {
    @Email(message = "Email must be valid")
    private String email;

    private String firstname;
    private String lastname;

    @Size(min = 10, max = 10, message = "Phone Number must be exactly 10 digits long")
	@Pattern(regexp = "^\\d{10}$", message = "Phone Number must contain only Numbers")
    private String phoneNumber;

    private String address;
}
