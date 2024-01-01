package com.project.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/user/product")
    public ResponseEntity<?> getAllProducts(
        @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
		@RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
		@RequestParam(name = "sortBy", defaultValue = "name", required = false) String sortBy,
		@RequestParam(name = "sortOrder", defaultValue = "asc", required = false) String sortOrder)
    {
        return null;
    }
}
