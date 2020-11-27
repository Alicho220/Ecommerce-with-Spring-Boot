package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Customer;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.model.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void testThatACustomerCanPlaceAnOrder(){
       Customer customer = customerRepository.findById(1).orElse(null);
       assertThat(customer).isNotNull();

       List<Product> products = productRepository.findAll();

       order.setDate("20-10-20");
       order.setDelivered(false);
       order.setCustomer(customer);
       order.setProducts(products);
       order.setCanceled(false);

       orderRepository.save(order);

        assertNotNull(order);
       assertThat(order.getId()).isNotNull();
    }

    @Test
    void testSaveOrderWithoutCustomer(){
        List<Product> products = productRepository.findAll();

        order.setDate("20-10-20");
        order.setDelivered(false);
        order.setCanceled(false);
        order.setProducts(products);

        assertThrows(OrderException.class, () -> {
            orderRepository.saveOrder(order);
        });
    }
    @Test
    void testSaveOrderWithoutOrder() throws OrderException {
        Order fakeOrder = new Order();
        assertThrows(OrderException.class, ()->{
            orderRepository.saveOrder(order);
        });
    }
    @Test
    void testSaveOrderWithoutProduct(){
        Optional<Customer> optionalCustomer = customerRepository.findById(1);
        Customer customer = optionalCustomer.get();
        assertNotNull(customer.getId());

        order.setDate("20-10-20");
        order.setDelivered(false);
        order.setCanceled(false);
        order.setCustomer(customer);

        assertThrows(OrderException.class, ()->{
            orderRepository.saveOrder(order);
        });

//        try{
//            orderRepository.saveOrder(order);
//
//        }catch (OrderException exp){
//            log.info(exp.getMessage());
//        }
//        assertNull(order.getId());
//
    }

}