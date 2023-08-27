package com.example.inventorymicroservice.repository;

import com.example.inventorymicroservice.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, Integer> {
    Inventory findByProductName(String productName);

    void deleteByProductName(String productName);

}
