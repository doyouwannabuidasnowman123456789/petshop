package com.project.ecommerce.controllers;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dto.SuccessResponseDTO;
import com.project.ecommerce.dto.UpdateUserRequestDTO;
import com.project.ecommerce.dto.UserDTO;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.exeptions.APIException;
import com.project.ecommerce.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new APIException("User not found");

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) throw new APIException("User not found");

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserInfomation(@Valid @RequestBody UpdateUserRequestDTO userRequestDTO, @PathVariable("id") UUID id){
        User user = userRepository.findById(id).orElse(null);

        if(user == null) throw new APIException("User not found");
        if (userRequestDTO.getEmail() != null) user.setEmail(userRequestDTO.getEmail());
        if (userRequestDTO.getFirstname() != null) user.setFirstname(userRequestDTO.getFirstname());
        if (userRequestDTO.getLastname() != null) user.setLastname(userRequestDTO.getLastname());
        if (userRequestDTO.getPhoneNumber() != null) user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        if (userRequestDTO.getAddress() != null) user.setAddress(userRequestDTO.getAddress());

        userRepository.save(user);
        return ResponseEntity.ok(new SuccessResponseDTO("success","User updated successfully"));
    }
}
