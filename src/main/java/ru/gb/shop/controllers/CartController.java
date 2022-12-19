package ru.gb.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.dto.Cart;
import ru.gb.shop.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/price_change")
    public void changeQuantity(@RequestParam Long productId, @RequestParam Integer delta) {
        cartService.changeQuantity(productId, delta);
    }

    @GetMapping("/delete/{productId}")
    public void deleteFromCart(@PathVariable Long productId) {
        cartService.deleteFromCart(productId);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }
}
