package com.example.inventorymicroservice.service;

import com.example.inventorymicroservice.model.Inventory;
import com.example.inventorymicroservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Integer getQuantity(String productName) {
        return (inventoryRepository.findByProductName(productName)).getQuantity();
    }

    public Inventory getInventory(String productName) {
        return inventoryRepository.findByProductName(productName);
    }

    public void addInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void updateInventory(Inventory inventory) {
        Inventory originalInventory = inventoryRepository.findByProductName(inventory.getProductName());
        if (originalInventory != null) {
            originalInventory.setQuantity(inventory.getQuantity());
            inventoryRepository.save(originalInventory);
        }

    }

    public void deleteInventory(String productName) {
        inventoryRepository.deleteByProductName(productName);
    }
}
