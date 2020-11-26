package com.ecommerce.data.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Address> addresses;

    public void setAddresses (Address address){
        if(addresses == null){
            addresses = new HashSet<>();
        }
        if (checkIfAddressExist(address)){
            addresses.add(address);
        }

    }
    public void setCards(Card card){
        if(card == null){
            cards = new HashSet<>();
        }
        cards.add(card);
    }

    private boolean checkIfAddressExist(Address address){
        if(!getAddresses().contains(address)){
            return true;
        }
        return false;
    }

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private Set<Card> cards;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Order> orders;
}
