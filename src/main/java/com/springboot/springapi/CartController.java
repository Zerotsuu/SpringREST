package com.springboot.springapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/cart")
public class CartController {

    private List<CartItem> cart = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<CartItem>> viewCart() {
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<CartItem> addItem(@RequestBody CartItem item) {
        item.setId((long) (cart.size() + 1));
        cart.add(item);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {
        Optional<CartItem> itemToRemove = cart.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();

        if (itemToRemove.isPresent()) {
            cart.remove(itemToRemove.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
