package ru.gb.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.entities.Product;
import ru.gb.shop.services.Cart;
import ru.gb.shop.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final Cart cart;

    @GetMapping
    public List<Product> getCartList() {
        return cart.getProductList();
    }

    @PostMapping("/add/{id}")
    public void addProductToCart(@RequestParam Long productId) {
        Product product = productService.findById(productId).orElseThrow(() ->
                new UsernameNotFoundException("Not found id: " + productId));
        cart.addProduct(product);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.findById(id).orElseThrow(()-> new UsernameNotFoundException("Product not found id: " + id));
        cart.delete(id);
    }
}
