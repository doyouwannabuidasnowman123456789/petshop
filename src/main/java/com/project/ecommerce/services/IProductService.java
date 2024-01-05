package com.project.ecommerce.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.dto.ProductDTO;
import com.project.ecommerce.dto.ProductResponseDTO;
import com.project.ecommerce.entities.Product;

public interface IProductService {
    // ProductDTO addProduct(Long categoryId, Product product);

	// ProductResponseDTO getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	// ProductResponseDTO searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy,
	// 		String sortOrder);

	// ProductDTO updateProduct(Long productId, Product product);

	ProductDTO updateProductWhenRemoveSpecialCategory(Long productId);

	// ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;

	// ProductResponseDTO searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
	// 		String sortOrder);

	String deleteProduct(Long productId);
}
