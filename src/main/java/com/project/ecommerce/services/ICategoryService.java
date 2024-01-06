package com.project.ecommerce.services;

import java.util.List;

import com.project.ecommerce.dto.CategoryDTO;
import com.project.ecommerce.dto.PaginationCategoryResponseDTO;
import com.project.ecommerce.dto.ProductDTO;
import com.project.ecommerce.entities.Category;

public interface ICategoryService {
    CategoryDTO createCategory(Category category);

	PaginationCategoryResponseDTO getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	CategoryDTO updateCategory(Category category, Long categoryId);

	String deleteCategory(Long categoryId);

	List<ProductDTO> getProductsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
