package com.example.productmicroservice.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class Inventory {
    @Id
    private ObjectId id;
    private String productName;
    private Integer quantity;

}
