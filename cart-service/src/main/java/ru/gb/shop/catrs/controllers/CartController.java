package ru.gb.shop.catrs.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.api.CartDto;
import ru.gb.shop.catrs.convertors.CartConvertor;
import ru.gb.shop.catrs.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConvertor cartConvertor;
    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConvertor.entityToDto(cartService.getCurrentCart());
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
