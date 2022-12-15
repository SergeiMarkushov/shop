package ru.gb.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.shop.dto.Cart;
import ru.gb.shop.entities.Product;
import ru.gb.shop.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Не удается добавить продукт с id: " + productId + " в корзину. Продукт не найден."));
        tempCart.add(product);
    }

    public void clearCart() {
        tempCart.getItems().clear();
    }

    public void deleteFromCart(Long productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Не удается удалить продукт с id: " + productId + ". Продукт не найден."));
        tempCart.delete(product);
    }

    public void changeQuantity(Long productId, Integer delta) {
        tempCart.changeQuantity(productId, delta);
    }
}
