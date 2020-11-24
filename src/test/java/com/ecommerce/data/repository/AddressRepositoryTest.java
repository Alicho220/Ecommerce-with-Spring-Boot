package com.ecommerce.data.repository;

import com.ecommerce.data.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    Address address;

    @BeforeEach
    void setUp() {

        address = new Address();
    }
    @Test
    void whenWeAddAddressToDb(){
        address.setCity("Yaba");
        address.setState("Lagos");
        address.setCountry("Nigeria");
        address.setZipcode("+234");
        address.setStreet("312 Herbert Macaulay way, Sabo");

        addressRepository.save(address);
        assertThat(address.getId()).isNotNull();


        log.info("address {}", address);
    }
    @Test
    void testThatWeCanUpdateAddress(){
        address=addressRepository.findById(1).orElse(null);
        address.setZipcode("100110");
        addressRepository.save(address);
        assertThat(address.getZipcode()).isEqualTo("100110");
        log.info("Address {}", address);
    }
    @Test
    void testThatWeCanDeleteAddress(){
        assertThat(addressRepository.existsById(1)).isTrue();
        addressRepository.deleteById(1);
        assertThat(addressRepository.existsById(1)).isFalse();
    }
    @Test
    void testThatWeCanGetAllAddress(){
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).isNotEmpty();
    }
}