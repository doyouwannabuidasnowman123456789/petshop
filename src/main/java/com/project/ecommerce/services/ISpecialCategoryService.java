package com.project.ecommerce.services;

import com.project.ecommerce.dto.CategoryDTO;
import com.project.ecommerce.dto.CategoryResponseDTO;
import com.project.ecommerce.entities.SpecialCategory;

public interface ISpecialCategoryService {
    CategoryDTO createCategory(SpecialCategory specialCategory);

	CategoryResponseDTO getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	CategoryDTO updateCategory(SpecialCategory specialCategory, Long specialCategoryId);

	String deleteCategory(Long specialCategoryId);
}
