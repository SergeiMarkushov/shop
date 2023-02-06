package ru.gb.shop.catrs.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.shop.api.ProductDto;
import ru.gb.shop.catrs.integrations.ProductServiceIntegration;
import ru.gb.shop.catrs.model.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!carts.containsKey(targetUuid)) {
            carts.put(targetUuid, new Cart());
        }
        return carts.get(targetUuid);
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).add(product);
    }

    public void clearCart(String uuid) {
        getCurrentCart(uuid).clearCart();
    }

    public void deleteFromCart(String uuid, Long productId) {
        getCurrentCart(uuid).delete(productId);
    }

    public void changeQuantity(String uuid, Long productId, Integer delta) {
        getCurrentCart(uuid).changeQuantity(productId, delta);
    }
}
