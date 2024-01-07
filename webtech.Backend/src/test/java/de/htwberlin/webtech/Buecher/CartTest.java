package de.htwberlin.webtech.Buecher;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.htwberlin.webtech.Buecher.model.Cart;
import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.repository.CartRepository;
import de.htwberlin.webtech.Buecher.service.CartService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class CartTest {


    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @Test
    public void AddBookToCart() {
        Cart cart = new Cart();
        cart.setId(UUID.randomUUID().toString());
        cart.setBooks(new ArrayList<>());

        Book book = new Book("978-3-16-148410-0", "Book", "Author",
                new BigDecimal("14.99"), "2021-01-01", null, null);
        cart.getBooks().add(book);

        Mockito.when(cartRepository.findById(any(String.class))).thenReturn(Optional.of(cart));
        Mockito.when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart updatedCart = cartService.addBookToCart(cart.getId(), book);

        assertNotNull(updatedCart);
        assertFalse(updatedCart.getBooks().isEmpty());
        assertEquals(book.getIsbn(), updatedCart.getBooks().get(0).getIsbn());
    }

    @Test
    public void CorrectCalculateTotalPrice() {
        Cart cart = new Cart();
        cart.setId(UUID.randomUUID().toString());
        List<Book> books = Arrays.asList(
                new Book("978-3-16-148410-0", "Book", "Author", new BigDecimal("14.99"), "2021-01-01", null, null),
                new Book("978-3-16-148410-1", "Book2", "Author", new BigDecimal("20.00"), "2021-02-01", null, null)
        );
        cart.setBooks(books);

        Mockito.when(cartRepository.findById(any(String.class))).thenReturn(Optional.of(cart));

        BigDecimal totalCost = cartService.calculateTotalCost(cart.getId());

        assertNotNull(totalCost);
        assertEquals(new BigDecimal("34.99"), totalCost);
    }


}


