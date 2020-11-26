package com.ecommerce.data.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    private Integer cvv;

    private String number;

    private String expDate;

    private String name;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Customer customer;

    public void setCustomer(Customer customer){
        if (getCustomer() == null){
            this.customer=customer;
        }
    }
}
