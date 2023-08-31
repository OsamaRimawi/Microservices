package com.example.inventorymicroservice.controller;

import com.example.inventorymicroservice.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("")
    public Integer getProductQuantity(
            @RequestParam String productName) {
        return inventoryService.getQuantity(productName);
    }


}
