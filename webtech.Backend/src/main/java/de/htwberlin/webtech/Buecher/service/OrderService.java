/*package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.repository.OrderRepository;
import de.htwberlin.webtech.Buecher.model.BookOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public BookOrder createBook(BookOrder bookOrder){
        bookOrder.setId(UUID.randomUUID().toString());
        orderRepository.save(bookOrder);
        return bookOrder;
    }

    public List<BookOrder> getAllBooks(){
        return orderRepository.findAll();
    }

    public List<BookOrder> searchByName(String username){
        return orderRepository.findByUserUsername(username);
    }
}
*/