package com.ecommerce.service.order;

import com.ecommerce.data.model.Order;
import com.ecommerce.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {

        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Integer id) {

        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);

    }

    @Override
    public List<Order> findAllOrders() {

        return orderRepository.findAll();
    }

    @Override
    public Order upDateOrder(Order order) {

        return orderRepository.save(order);
    }
}
