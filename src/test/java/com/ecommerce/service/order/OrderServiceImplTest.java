package com.ecommerce.service.order;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;


    @InjectMocks
    OrderService orderService = new OrderServiceImpl();

    Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
    }
    @Test
    void testThatWeCanCallTheSaveOrderRepository() throws OrderException {
        when(orderRepository.save(order)).thenReturn(order);
        orderService.saveOrder(order);
        verify(orderRepository, times(1)).saveOrder(order);
    }
}