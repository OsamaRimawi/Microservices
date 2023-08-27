package com.example.productmicroservice.service;


import com.example.productmicroservice.DTO.ProductDTO;
import com.example.productmicroservice.mapper.ProductDTOMapper;
import com.example.productmicroservice.model.Product;
import com.example.productmicroservice.model.Category;

import com.example.productmicroservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    //private final InventoryRepository inventoryRepository;


    private final ProductDTOMapper productDTOMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;

    }

    public List<ProductDTO> getAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product -> {
            ProductDTO productDTO = productDTOMapper.productToProductDTO(product);
           // Integer quantity = (inventoryRepository.findByProductName(product.getName())).getQuantity();
            //productDTO.setQuantity(quantity);
            return productDTO;
        }).collect(Collectors.toList());
    }

    public ProductDTO getProductByNameAndCategory(String name, String category) {

        if (name == null || name.isEmpty() || category == null) {
            throw new IllegalArgumentException("Invaild name and category parameters");
        }
        Category realCategory = Category.valueOf(category);
        Product product = productRepository.findByNameAndCategory(name, realCategory);
        ProductDTO productDTO = productDTOMapper.productToProductDTO(product);
//        Integer quantity = (inventoryRepository.findByProductName(product.getName())).getQuantity();
//        productDTO.setQuantity(quantity);
        return productDTO;
    }

    public ResponseEntity<String> addProduct(ProductDTO productDTO) {
        Product product = productDTOMapper.productDTOToProduct(productDTO);
//        Inventory inventory = new Inventory();
//        inventory.setProductName(product.getName());
//        inventory.setQuantity(productDTO.getQuantity());
//        inventoryRepository.save(inventory);
        productRepository.save(product);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateProduct(String name, ProductDTO productDTO) {
        Product existingProduct = productRepository.findByName(name);
//        Inventory existingInventory = inventoryRepository.findByProductName(name);
//        productDTOMapper.updateProductFromDTO(productDTO, existingProduct);
//        existingInventory.setQuantity(productDTO.getQuantity());
//        productRepository.save(existingProduct);
//        inventoryRepository.save(existingInventory);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<String> deleteProductByName(String name) {
        productRepository.deleteProductByName(name);
//        inventoryRepository.deleteByProductName(name);
        return new ResponseEntity<>("success", HttpStatus.NO_CONTENT);
    }
}
