package com.project.ecommerce.services;

import java.io.IOException;

import com.project.ecommerce.dto.ProductDTO;
import com.project.ecommerce.dto.ProductRequestDTO;
public interface IProductService {
    ProductDTO addProduct(ProductRequestDTO productRequestDTO) throws IOException;

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
