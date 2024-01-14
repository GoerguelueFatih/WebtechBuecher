package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.ExtraCode.OrderDTO;
import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.model.Cart;
import de.htwberlin.webtech.Buecher.model.Order;
import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("(Search) Order not found with id: " + orderId));
    }

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


    public List<OrderDTO> getAllOrderDTOs() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> {
            List<String> bookTitles = order.getBooks().stream()
                    .map(Book::getTitle)
                    .collect(Collectors.toList());
            String formattedDate = order.getLocalDateTime().toString();
            return new OrderDTO(
                    order.getId(),
                    order.getTotal(),
                    order.getStatus(),
                    formattedDate,
                    bookTitles
            );
        }).collect(Collectors.toList());
    }



    @Transactional
    public Order purchaseCart(String cartId, String oktaUserId) {
        Cart cart = cartService.getCartById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        User user = userService.getUserById(oktaUserId);

        if (cart.getBooks().isEmpty()) {
            throw new RuntimeException("Cannot purchase an empty cart");
        }

        String orderId = UUID.randomUUID().toString();

        Order order = Order.builder()
                .id(orderId)
                .user(user)
                .books(new ArrayList<>(cart.getBooks()))
                .total(cartService.calculateTotalCost(cartId))
                .status(Order.OrderStatus.PURCHASED)
                .build();

        return createOrder(order);
    }

}
