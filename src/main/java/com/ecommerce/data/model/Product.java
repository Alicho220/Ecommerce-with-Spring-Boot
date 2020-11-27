package com.ecommerce.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private String expDate;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;


}
