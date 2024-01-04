package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dto.CategoryDTO;
import com.project.ecommerce.dto.CategoryRequestDTO;
import com.project.ecommerce.dto.CategoryResponseDTO;
import com.project.ecommerce.entities.Category;
import com.project.ecommerce.services.CategoryService;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryRequestDTO body) {
        Category category = new Category();
        category.setName(body.getName());
        
        CategoryDTO categoryDTO = categoryService.createCategory(category);
        
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("")
    public ResponseEntity<CategoryResponseDTO> getMethodName(@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
    @RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
    @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
    @RequestParam(name = "sortOrder", defaultValue = "asc", required = false) String sortOrder) {
        CategoryResponseDTO categoryResponseDTO = categoryService.getCategories(pageNumber, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(categoryResponseDTO);
    }
}
