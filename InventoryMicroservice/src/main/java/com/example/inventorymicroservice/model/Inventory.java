package com.example.inventorymicroservice.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "inventories")
public class Inventory {
    @Id
    private ObjectId id;
    private String productName;
    private Integer quantity;

}
