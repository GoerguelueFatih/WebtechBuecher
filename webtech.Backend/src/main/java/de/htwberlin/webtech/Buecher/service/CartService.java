package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.model.Cart;
import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.repository.CartRepository;
import de.htwberlin.webtech.Buecher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Transactional
    public Cart createCart(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Cart cart = Cart.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .build();
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public Cart updateCart(String id, Cart updatedCart) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + id));

        cart.setBooks(updatedCart.getBooks());
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart addBookToCart(String cartId, Book book) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));

        cart.getBooks().add(book);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeBookFromCart(String cartId, Book book) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));

        cart.getBooks().remove(book);
        return cartRepository.save(cart);
    }

    @Transactional
    public void deleteCart(String cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
        cartRepository.delete(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Transactional
    public void clearCart(String cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));

        cart.getBooks().clear();
        cartRepository.save(cart);
    }

    public BigDecimal calculateTotalCost(String cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
        return cart.getBooks().stream()
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }}
