package com.ecommerce.data.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;

    private String state;

    private String city;

    private String zipcode;

    private String street;

    @ManyToMany(mappedBy = "addresses")
    @ToString.Exclude
    private List<Customer> customers;

    public void setCustomer (Customer customer){
        if (customer == null){
            customers = new ArrayList<>();
        }
        customers.add(customer);
    }
}
