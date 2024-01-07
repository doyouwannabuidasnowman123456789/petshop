package com.project.ecommerce.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.dto.ProductDTO;
import com.project.ecommerce.dto.ProductRequestDTO;
import com.project.ecommerce.dto.ProductResponseDTO;
import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.Category;
import com.project.ecommerce.entities.Product;
import com.project.ecommerce.exeptions.APIException;
import com.project.ecommerce.exeptions.ResourceNotFoundException;
import com.project.ecommerce.repositories.CategoryRepository;
import com.project.ecommerce.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ProductService implements IProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${project.image}")
    private String path;

    @Override
    public ProductDTO addProduct(ProductRequestDTO productRequestDTO) throws IOException {
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId",
                        productRequestDTO.getCategoryId()));

        boolean isProductNotPresent = true;

        List<Product> products = category.getProducts();

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getTitle().equals(productRequestDTO.getTitle())
                    && products.get(i).getDescription().equals(productRequestDTO.getDescription())) {

                isProductNotPresent = false;
                break;
            }
        }

        if (isProductNotPresent) {            
            Product product = new Product(); // Create a new instance of Product
            product.setCategory(category);
            product.setPetType(productRequestDTO.getPetType());
            product.setTitle(productRequestDTO.getTitle());
            product.setDescription(productRequestDTO.getDescription());
            product.setPrice(productRequestDTO.getPrice());
            product.setImages(new ArrayList<String>());

            for(MultipartFile image: productRequestDTO.getImages()) {
                String imageName = fileService.uploadImage(path, image);
                product.getImages().add(imageName);
            }

            Product savedProduct = productRepository.save(product);

            return modelMapper.map(savedProduct, ProductDTO.class);
        } else {
            throw new APIException("Product already exists !!!");
        }
    }

    // @Override
    // public ProductResponseDTO getAllProducts(Integer pageNumber, Integer
    // pageSize, String sortBy, String sortOrder) {
    // Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
    // Sort.by(sortBy).ascending()
    // : Sort.by(sortBy).descending();

    // Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

    // Page<Product> pageProducts = productRepository.findAll(pageDetails);

    // List<Product> products = pageProducts.getContent();

    // List<ProductDTO> productDTOs = products.stream().map(product ->
    // modelMapper.map(product, ProductDTO.class))
    // .collect(Collectors.toList());

    // ProductResponseDTO productResponse = new ProductResponseDTO();

    // productResponse.setData(productDTOs);
    // productResponse.setPageNumber(pageProducts.getNumber());
    // productResponse.setPageSize(pageProducts.getSize());
    // productResponse.setTotalElements(pageProducts.getTotalElements());
    // productResponse.setTotalPages(pageProducts.getTotalPages());
    // productResponse.setLastPage(pageProducts.isLast());

    // return productResponse;
    // }

    // @Override
    // public ProductResponseDTO searchByCategory(Long categoryId, Integer
    // pageNumber, Integer pageSize, String sortBy,
    // String sortOrder) {
    // Category category = categoryRepository.findById(categoryId)
    // .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId",
    // categoryId));

    // Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
    // Sort.by(sortBy).ascending()
    // : Sort.by(sortBy).descending();

    // Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

    // Page<Product> pageProducts = productRepository.findAll(pageDetails);

    // List<Product> products = pageProducts.getContent();

    // if (products.size() == 0) {
    // throw new APIException(category.getName() + " category doesn't contain any
    // products !!!");
    // }

    // List<ProductDTO> productDTOs = products.stream().map(p -> modelMapper.map(p,
    // ProductDTO.class))
    // .collect(Collectors.toList());

    // ProductResponseDTO productResponse = new ProductResponseDTO();

    // productResponse.setData(productDTOs);
    // productResponse.setPageNumber(pageProducts.getNumber());
    // productResponse.setPageSize(pageProducts.getSize());
    // productResponse.setTotalElements(pageProducts.getTotalElements());
    // productResponse.setTotalPages(pageProducts.getTotalPages());
    // productResponse.setLastPage(pageProducts.isLast());

    // return productResponse;
    // }

    // @Override
    // public ProductDTO updateProduct(Long productId, Product product) {
    // return null;
    // }

    @Override
    public ProductDTO updateProductWhenRemoveSpecialCategory(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        product.setSpecialCategory(null);

        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    // @Override
    // public ProductDTO updateProductImage(Long productId, MultipartFile image)
    // throws IOException {
    // return null;
    // }

    // @Override
    // public ProductResponseDTO searchProductByKeyword(String keyword, Integer
    // pageNumber, Integer pageSize,
    // String sortBy, String sortOrder) {
    // Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
    // Sort.by(sortBy).ascending()
    // : Sort.by(sortBy).descending();

    // Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

    // Page<Product> pageProducts = productRepository.findByNameLike(keyword,
    // pageDetails);

    // List<Product> products = pageProducts.getContent();

    // if (products.size() == 0) {
    // throw new APIException("Products not found with keyword: " + keyword);
    // }

    // List<ProductDTO> productDTOs = products.stream().map(p -> modelMapper.map(p,
    // ProductDTO.class))
    // .collect(Collectors.toList());

    // ProductResponseDTO productResponse = new ProductResponseDTO();

    // productResponse.setData(productDTOs);
    // productResponse.setPageNumber(pageProducts.getNumber());
    // productResponse.setPageSize(pageProducts.getSize());
    // productResponse.setTotalElements(pageProducts.getTotalElements());
    // productResponse.setTotalPages(pageProducts.getTotalPages());
    // productResponse.setLastPage(pageProducts.isLast());

    // return productResponse;
    // }

    @Override
    public String deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        // List<Cart> carts = cartRepo.findCartsByProductId(productId);

        // carts.forEach(cart -> cartService.deleteProductFromCart(cart.getCartId(),
        // productId));
        productRepository.delete(product);
        return "Product with productId: " + productId + " deleted successfully !!!";
    }
}
