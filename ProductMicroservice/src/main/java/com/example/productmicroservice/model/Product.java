package com.example.productmicroservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", updatable = false, nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "100000")
    private BigDecimal price;

    @Column(name = "creationTime")
    private Long creationTime;

    @Column(name = "lastUpdateTime")
    private Long lastUpdateTime;


    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "version", updatable = false)
    private String version = "v1.0";

}
