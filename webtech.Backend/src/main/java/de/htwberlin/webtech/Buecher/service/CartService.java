package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.model.Cart;
import de.htwberlin.webtech.Buecher.repository.CartRepository;
import de.htwberlin.webtech.Buecher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public Cart createCart(Cart cart) {
        cart.setId(UUID.randomUUID().toString());
        cart.setLocalDateTime(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }


    @Transactional
    public Cart updateCart(String id, Cart updatedCart) {
        return cartRepository.findById(id).map(cart -> {
            cart.setUser(updatedCart.getUser());
            cart.setBooks(updatedCart.getBooks());
            return cartRepository.save(cart);
        }).orElseThrow(()
                -> new IllegalArgumentException("Cart not found with id: " + id));
    }


    @Transactional
    public void deleteCart(String id) {
        cartRepository.deleteById(id);
    }


    @Transactional
    public Cart addBookToCart(String cartId, Book book) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()
                -> new IllegalArgumentException("Cart not found with id: " + cartId));
        cart.getBooks().add(book);
        return cartRepository.save(cart);
    }


    @Transactional
    public Cart removeBookFromCart(String cartId, Book book) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()
                -> new IllegalArgumentException("Cart not found with id: " + cartId));
        cart.getBooks().remove(book);
        return cartRepository.save(cart);
    }


    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }



    public BigDecimal calculateTotalCost(String cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()
                -> new IllegalArgumentException("Cart not found with id: " + cartId));
        return cart.getBooks().stream()
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Transactional
    public void clearCart(String cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new RuntimeException("Cart not found with id: " + cartId)
        );

        if (!cart.getBooks().isEmpty()) {
            cart.getBooks().clear();
            cartRepository.save(cart);
        }
    }


}
