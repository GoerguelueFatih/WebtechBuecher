/*package de.htwberlin.webtech.Buecher.controller;

import de.htwberlin.webtech.Buecher.model.BookOrder;
import de.htwberlin.webtech.Buecher.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public BookOrder createBook(@RequestBody BookOrder bookOrder){
        return orderService.createBook(bookOrder);
    }
}
*/