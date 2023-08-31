package com.example.productmicroservice.controller;


import com.example.productmicroservice.DTO.ProductDTO;
import com.example.productmicroservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;

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
    @Operation(
            summary = "Get all products",
            description = "Retrieves a list of all products available in the database."
    )
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
    @Operation(
            summary = "Get product by name and category",
            description = "Retrieve a product from the database based on its name and category."
    )
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
    @Operation(
            summary = "Add a new product",
            description = "Add a new product to the database."
    )
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
    @Operation(
            summary = "Update a product",
            description = "Update an existing product in the database."
    )
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
    @Operation(
            summary = "Delete a product by name",
            description = "Delete a product from the database based on its name."
    )
    public ResponseEntity<String> deleteProduct(
            @RequestParam String name) {
        return productService.deleteProductByName(name);
    }

    /**
     * Performs Check if exist  operation .
     *
     * @param name The Product DTO Json
     * @return boolean
     */
    @GetMapping("/check")
    @Operation(
            summary = "Check if a product exists",
            description = "Check if a product exists in the database based on its name."
    )
    public boolean doesProductExist(
            @RequestParam String name) {
        return productService.doesProductExist(name);
    }
}
