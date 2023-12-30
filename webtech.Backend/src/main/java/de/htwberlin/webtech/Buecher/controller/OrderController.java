package de.htwberlin.webtech.Buecher.controller;

import de.htwberlin.webtech.Buecher.model.Order;
import de.htwberlin.webtech.Buecher.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

}
