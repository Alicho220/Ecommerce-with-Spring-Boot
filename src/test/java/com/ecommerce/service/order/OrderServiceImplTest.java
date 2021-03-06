package com.ecommerce.service.order;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

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
        when(orderRepository.saveOrder(order)).thenReturn(order);
        orderService.saveOrder(order);
        verify(orderRepository, times(1)).saveOrder(order);
    }
    @Test
    void testThatWeCanCallTheFindOrderByIdRepository(){
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        orderService.findOrderById(1);
        verify(orderRepository, times(1)).findById(1);
    }
    @Test
    void testThatWeCanCallTheFindAllOrderRepository(){
//        List<Order> orders;
        when(orderRepository.findAll()).thenReturn(List.of(order));
        orderService.findAllOrders();
        verify(orderRepository, times(1)).findAll();
    }
    @Test
    void testThatWeCanCallTheDeleteOrderByIdRepository(){
        doNothing().when(orderRepository).deleteById(1);
        orderService.deleteById(1);
        verify(orderRepository, times(1)).deleteById(1);
    }
    @Test
    void testThatWeCallTheUpDateOrderByIdRepository(){
        when(orderRepository.save(order)).thenReturn(order);
        orderService.saveOrder(order);
        verify(orderRepository, times(1)).save(order);
    }

}