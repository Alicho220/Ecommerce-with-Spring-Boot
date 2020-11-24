package com.ecommerce.data.repository;

import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }
    @Test
    void testThatWeCanSaveCustomer(){
        customer.setContact("07039461453");
        customer.setEmail("alichoojimmy@gmail.com");
        customer.setFirstName("Chukwemeka");
        customer.setLastName("Okorie");
        customer.setPassword("my123");

        Address address = new Address();
        address.setZipcode("001001");
        address.setStreet("Folarin Street");
        address.setCountry("Nigeria");
        address.setState("Lagos");
        address.setCity("Lekki");

        customer.setAddresses(address);


        log.info("customer before saving{}", customer);
        customerRepository.save(customer);
        assertThat(customer.getId()).isNotNull();


        log.info("customer before saving{}", customer);
    }

    @Test
    void testThatTwoCustomersCanShareOneAddress(){
        customer.setPassword("");
        customer.setLastName("Tobi");
        customer.setFirstName("Femi");
        customer.setContact("08035851287");
        customer.setEmail("tobifemi@gmail.com");

        Address address = addressRepository.findById(4).orElse(null);

        customer.setAddresses(address);

        customerRepository.save(customer);
        log.info("customer {}", customer);

        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getAddresses()).isNotEmpty();

    }
    @Test
    @Transactional
    @Rollback(value = false)
    void testThatOneCustomerCanHaveMultipleAdddress(){
        customer = customerRepository.findById(2).orElse(null);

        Address address = addressRepository.findById(3).orElse(null);

        customer.setAddresses(address);

        customerRepository.save(customer);

        log.info("customer {}", customer);

        assertThat(customer.getAddresses().size()).isEqualTo(2);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanFetchAllCustomerAddress(){
        customer= customerRepository.findById(2).orElse(null);

        assert customer != null;
        for (Address address: customer.getAddresses()){
            log.info("Address {}", address);
        }
        assertThat(customer.getAddresses().size()).isEqualTo(2);
//         log.info("All addresses {}", customer.getAddresses().get(1));
    }
    @Test
    @Transactional
    @Rollback
    void testThatWeCanRemoveAnAddressFromACustomerAddressesList(){
        customer = customerRepository.findById(2).orElse(null);

        assert customer != null;

        Address address = addressRepository.findById(3).orElse(null);

        if (customer.getAddresses().contains(address)){
            customer.getAddresses().remove(address);
        }
        assertThat(customer.getAddresses().size()).isEqualTo(1);
    }
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void testThatWeCanRemoveAddressFromACustomerAddressesList(){
//        customer= customerRepository.findById(2).orElse(null);
//
//        Address address = addressRepository.findById(3).orElse(null);
//
//        if (customer.getAddresses().contains(address)){
//            customer.getAddresses().remove(customer.getAddresses().lastIndexOf(address));
//        }
//        assertThat(customer.getAddresses().size()).isEqualTo(1);
//
//    }

}
