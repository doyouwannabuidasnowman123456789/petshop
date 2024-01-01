package com.project.ecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    public Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
