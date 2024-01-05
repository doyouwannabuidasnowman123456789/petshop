package com.project.ecommerce.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dto.ProductDTO;
import com.project.ecommerce.dto.ProductRequestDTO;
import com.project.ecommerce.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(
        @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
		@RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
		@RequestParam(name = "sortBy", defaultValue = "name", required = false) String sortBy,
		@RequestParam(name = "sortOrder", defaultValue = "asc", required = false) String sortOrder)
    {
        return null;
    }

    @PostMapping("")
    public ResponseEntity<ProductDTO> addProduct(
        @Valid 
        @ModelAttribute 
        ProductRequestDTO productRequestDTO
    ) throws IOException {
        ProductDTO productDTO = productService.addProduct(productRequestDTO);

        return ResponseEntity.ok(productDTO);
    }
}
