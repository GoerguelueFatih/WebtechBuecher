package de.htwberlin.webtech.Buecher.controller;

import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.model.Cart;
import de.htwberlin.webtech.Buecher.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @CrossOrigin
    @PostMapping("/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable String userId) {
        try {
            Cart newCart = cartService.createCart(userId);
            return ResponseEntity.ok(newCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable String id) {
        return cartService.getCartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable String id, @RequestBody Cart updatedCart) {
        try {
            return ResponseEntity.ok(cartService.updateCart(id, updatedCart));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PostMapping("/{cartId}/books")
    public ResponseEntity<Cart> addBookToCart(@PathVariable String cartId, @RequestBody Book book) {
        try {
            return ResponseEntity.ok(cartService.addBookToCart(cartId, book));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{cartId}/books")
    public ResponseEntity<Cart> removeBookFromCart(@PathVariable String cartId, @RequestBody Book book) {
        try {
            return ResponseEntity.ok(cartService.removeBookFromCart(cartId, book));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }
}
