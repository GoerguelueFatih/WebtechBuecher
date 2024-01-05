package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.model.Cart;
import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.repository.OrderRepository;
import de.htwberlin.webtech.Buecher.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;

    // Create a new order
    @Transactional
    public Order createOrder(Order order) {
        order.setId(UUID.randomUUID().toString());
        order.setLocalDate(LocalDate.now());
        return orderRepository.save(order);
    }

    // Retrieve an order by its ID
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("(Search) Order not found with id: " + orderId));
    }

    // Update an existing order
    @Transactional
    public Order updateOrder(String orderId, Order updatedOrder) {
        return orderRepository.findById(orderId).map(order -> {
            order.setStatus(updatedOrder.getStatus());
            order.setTotal(updatedOrder.getTotal());
            order.setBooks(updatedOrder.getBooks());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("(Update) Order not found with id: " + orderId));
    }


    @Transactional
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    @Transactional
    public Order purchaseCart(String cartId, String userId) {
        Cart cart = cartService.getCartById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        User user = userService.getUserById(userId);

        if (cart.getBooks().isEmpty()) {
            throw new RuntimeException("Cannot purchase an empty cart");
        }

        Order order = Order.builder()
                .user(user)
                .books(new ArrayList<>(cart.getBooks()))
                .total(cartService.calculateTotalCost(cartId))
                .status("Purchased")
                .localDate(LocalDate.now())
                .build();

        order = createOrder(order);
        cartService.clearCart(cartId);

        return order;
    }
}

