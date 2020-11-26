package com.ecommerce.service.product;

import com.ecommerce.data.model.Product;
import com.ecommerce.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService = new ProductServiceImpl();

    Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
    }
    @Test
    void testThatWeCanCallTheSaveProductRepository(){
        when(productRepository.save(product)).thenReturn(product);
        productService.saveProduct(product);
        verify(productRepository, times(1)).save(product);
    }
    @Test
    void testThatWeCanCallTheFindProductRepository(){
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        productService.findProductById(1);
        verify(productRepository, times(1)).findById(1);
    }



}