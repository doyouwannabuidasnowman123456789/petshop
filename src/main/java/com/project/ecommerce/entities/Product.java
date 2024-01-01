package com.project.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;
    private String image;

    @NotBlank
    @Size(min = 6, max = 255, message = "Product description must between 6 and 255 characters length")
    private String description;

    @NotBlank
    private Integer quantity;
    private double price;
    private double discount;
    private Integer rating;

    @ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
