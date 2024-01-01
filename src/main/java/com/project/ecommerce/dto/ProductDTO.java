package com.project.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
	private String name;
	private String image;
	private String description;
	private Integer quantity;
	private double price;
	private double discount;
	private double rating;
}