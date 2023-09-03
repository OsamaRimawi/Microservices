package com.example.productmicroservice.service;


import brave.Span;
import brave.Tracer;
import com.example.productmicroservice.DTO.ProductDTO;
import com.example.productmicroservice.mapper.ProductDTOMapper;
import com.example.productmicroservice.model.Category;
import com.example.productmicroservice.model.Inventory;
import com.example.productmicroservice.model.Product;
import com.example.productmicroservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final WebClient.Builder webClientBuilder;


    private final ProductDTOMapper productDTOMapper;
    private final Tracer tracer;

    @Autowired
    public ProductService(ProductRepository productRepository, WebClient.Builder webClientBuilder, ProductDTOMapper productDTOMapper, Tracer tracer) {
        this.productRepository = productRepository;
        this.webClientBuilder = webClientBuilder;
        this.productDTOMapper = productDTOMapper;
        this.tracer = tracer;
    }

    public List<ProductDTO> getAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product -> {
            ProductDTO productDTO = productDTOMapper.productToProductDTO(product);
            Span inventoryServiceGetQuantitySpan = tracer.nextSpan().name("InventoryServiceGetQuantity");
            try (Tracer.SpanInScope spanInScope = tracer.withSpanInScope(inventoryServiceGetQuantitySpan.start())) {
                Integer quantity = webClientBuilder.build().get()
                        .uri("http://inventory-microservice/api/v1.0/inventory/quantity",
                                uriBuilder -> uriBuilder.queryParam("productName", product.getName()).build())
                        .retrieve()
                        .bodyToMono(Integer.class)
                        .block();
                productDTO.setQuantity(quantity);

            } finally {
                inventoryServiceGetQuantitySpan.finish();
            }

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
        Integer quantity = webClientBuilder.build().get()
                .uri("http://inventory-microservice/api/v1.0/inventory/quantity",
                        uriBuilder -> uriBuilder.queryParam("productName", product.getName()).build())
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        productDTO.setQuantity(quantity);
        return productDTO;
    }

    public ResponseEntity<String> addProduct(ProductDTO productDTO) {
        Product product = productDTOMapper.productDTOToProduct(productDTO);
        Inventory inventory = new Inventory();
        inventory.setProductName(product.getName());
        inventory.setQuantity(productDTO.getQuantity());

        webClientBuilder.build()
                .post()
                .uri("http://inventory-microservice/api/v1.0/inventory")
                .body(BodyInserters.fromValue(inventory))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        productRepository.save(product);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateProduct(String name, ProductDTO productDTO) {
        Product existingProduct = productRepository.findByName(name);
        Inventory inventory = new Inventory();
        inventory.setProductName(name);
        inventory.setQuantity(productDTO.getQuantity());
        productDTOMapper.updateProductFromDTO(productDTO, existingProduct);

        webClientBuilder.build()
                .put()
                .uri("http://inventory-microservice/api/v1.0/inventory")
                .body(BodyInserters.fromValue(inventory))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        productRepository.save(existingProduct);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<String> deleteProductByName(String name) {
        webClientBuilder.build()
                .delete()
                .uri("http://inventory-microservice/api/v1.0/inventory",
                        uriBuilder -> uriBuilder.queryParam("productName", name).build())
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        productRepository.deleteProductByName(name);
        return new ResponseEntity<>("success", HttpStatus.NO_CONTENT);
    }

    public boolean doesProductExist(String name) {
        return productRepository.existsByName(name);
    }
}
