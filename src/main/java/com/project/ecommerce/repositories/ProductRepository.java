package com.project.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    Page<Product> findByNameLike(String keyword, Pageable pageDetails);
}
