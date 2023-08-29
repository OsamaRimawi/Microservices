package com.example.inventorymicroservice.service;

import com.example.inventorymicroservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Integer getQuantity(String productName){
        return (inventoryRepository.findByProductName(productName)).getQuantity();
    }
}
