package ru.gb.shop.catrs.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.shop.api.ProductDto;
import ru.gb.shop.catrs.integrations.ProductServiceIntegration;
import ru.gb.shop.catrs.model.Cart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void clearCart() {
        tempCart.clearCart();
    }

    public void deleteFromCart(Long productId) {
        tempCart.delete(productId);
    }

    public void changeQuantity(Long productId, Integer delta) {
        tempCart.changeQuantity(productId, delta);
    }
}
