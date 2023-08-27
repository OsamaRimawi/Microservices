package com.example.productmicroservice.repository;


import com.example.productmicroservice.model.Category;
import com.example.productmicroservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByNameAndCategory(String name, Category category);

    Product findByName(String name);

    void deleteProductByName(String name);

    boolean existsByName(String name);


}
