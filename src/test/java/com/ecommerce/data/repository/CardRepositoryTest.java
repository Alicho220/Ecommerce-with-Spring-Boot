package com.ecommerce.data.repository;

import com.ecommerce.data.model.Card;
import com.ecommerce.data.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts="classpath:db/insert.sql")
class CardRepositoryTest {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    Card card;
    @BeforeEach
    void setUp() {
        card = new Card();
    }
    @Test
    void testThatWeCanSaveCard(){
        card.setName("Okorie Chukwuemeka");
        card.setNumber("5444889922336756");
        card.setType("Master");
        card.setCvv(344);
        card.setExpDate("12-10-23");

        Customer customer = customerRepository.findById(1).orElse(null);

//        assert customer != null;
//        card.setCustomer(customer);
        cardRepository.save(card);
        assertThat(customer).isNotNull();
        assertThat(card.getId()).isNotNull();

        log.info("card {}", card);
    }
    @Test
    void testThatOneCustomerCanHaveMultipleCard(){
        card = cardRepository.findById(2).orElse(null);

        assertThat(card).isNotNull();
//        assertThat(card.getCustomer()).isNotNull();

        Customer customer = customerRepository.findById(1).orElse(null);
        assertThat(customer).isNotNull();

        card.setCustomer(customer);

        cardRepository.save(card);

        assertThat(card.getId()).isNotNull();
        assertThat(card.getCustomer()).isNotNull();

        log.info("card {}",card);

    }
}