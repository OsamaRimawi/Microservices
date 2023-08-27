package com.example.productmicroservice.controller;


import com.example.productmicroservice.DTO.ProductDTO;
import com.example.productmicroservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Rest Controller Class that handles CRUD Operations for Online Products Database
 */
@RestController
@RequestMapping("${product.api.base-url}")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {

        this.productService = productService;

    }

    /**
     * Performs Get ALL operation .
     *
     * @return All Products in Database
     */
    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {
        return productService.getAll();
    }

    /**
     * Performs Get Product operation .
     *
     * @param name     The name of the product.
     * @param category The Category of the product.
     * @return products in Database
     */
    @GetMapping("")
    public ProductDTO getProductByNameAndCategory(
            @RequestParam String name,
            @RequestParam String category) {
        return productService.getProductByNameAndCategory(name, category);
    }

    /**
     * Performs Create Product operation .
     *
     * @param productDTO The Product DTO Json
     * @return responseEntityCreated
     */
    @PostMapping
    public ResponseEntity<String> addOne(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    /**
     * Performs Update Product operation .
     *
     * @param productDTO The Product DTO Json
     * @return responseEntityUpdated
     */
    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO.getName(), productDTO);
    }

    /**
     * Performs Delete Product operation .
     *
     * @param name The Product DTO Json
     * @return responseEntityDeleted
     */
    @DeleteMapping
    public ResponseEntity<String> deleteProduct(
            @RequestParam String name) {
        return productService.deleteProductByName(name);
    }

}
