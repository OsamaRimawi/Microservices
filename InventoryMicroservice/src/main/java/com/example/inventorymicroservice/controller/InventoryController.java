package com.example.inventorymicroservice.controller;

import com.example.inventorymicroservice.model.Inventory;
import com.example.inventorymicroservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller Class that handles Operations for Inventories
 */
@RestController
@RequestMapping("${inventory.api.base-url}")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    /**
     * Performs Get Quantity operation .
     *
     * @return Quantity of product in Database
     */
    @GetMapping("/quantity")
    public Integer getProductQuantity(
            @RequestParam String productName) {
        return inventoryService.getQuantity(productName);
    }

    /**
     * Performs Get inventory operation .
     *
     * @return inventory in Database
     */
    @GetMapping("")
    public Inventory getInventory(
            @RequestParam String productName) {
        return inventoryService.getInventory(productName);
    }

    /**
     * Performs Create inventory operation .
     *
     * @param inventory The inventory Json
     * @return void
     */
    @PostMapping
    public void addOneInventory(@RequestBody Inventory inventory) {
        inventoryService.addInventory(inventory);
    }

    /**
     * Performs Update inventory operation .
     *
     * @param inventory The inventory  Json
     * @return void
     */
    @PutMapping
    public void updateInventory(@RequestBody Inventory inventory) {

        inventoryService.updateInventory(inventory);
    }

    /**
     * Performs Delete inventory operation .
     *
     * @param productName
     * @return void
     */
    @DeleteMapping
    public void deleteInventory(
            @RequestParam String productName) {
        inventoryService.deleteInventory(productName);
    }

}
