package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.repository.OrderRepository;
import de.htwberlin.webtech.Buecher.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(Order order){
        order.setId(UUID.randomUUID().toString());
        orderRepository.save(order);
        return order;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> searchByName(String username){
        return orderRepository.findByUserUsername(username);
    }

}
