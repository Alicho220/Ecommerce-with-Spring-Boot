package com.ecommerce.data.repository;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    Product product;

    @BeforeEach
    void setUp() {

        product = new Product();
    }
    @Test
    @Transactional
    @Rollback
    void testThatWeCanAddProduct(){
        product.setName("Gala");
        product.setExpDate("10-12-20");
        product.setPrice(35.5);
        product.setQuantity(1);
        product.setDescription("Eat when Hot");

        productRepository.save(product);
        assertThat(product.getId()).isNotNull();
        log.info("product {}",product);

    }
    @Test
    void testThatWeCanDeleteAProduct(){
        assertThat(productRepository.existsById(1)).isTrue();
        productRepository.deleteById(1);
        assertThat(productRepository.existsById(1)).isFalse();
    }

    
}