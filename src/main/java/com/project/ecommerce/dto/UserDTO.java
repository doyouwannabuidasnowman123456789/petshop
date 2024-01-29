package com.project.ecommerce.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;
}
