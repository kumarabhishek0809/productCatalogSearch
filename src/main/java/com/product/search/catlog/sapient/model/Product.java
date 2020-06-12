package com.product.search.catlog.sapient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Cacheable
public class Product {

    @Id
    @GeneratedValue
    private int productId;

    private String productName;
    private double price;
    private String color;
    private String brand;
    private int size;
    private String sku;
    private String seller;
}
