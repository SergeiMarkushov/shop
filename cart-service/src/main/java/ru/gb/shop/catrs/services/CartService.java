package ru.gb.shop.catrs.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.shop.catrs.integrations.ProductServiceIntegration;
import ru.gb.shop.catrs.model.Cart;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart)redisTemplate.opsForValue().get(targetUuid);
    }

    public void add(String uuid, Long productId) {
        execute(uuid, cart -> cart.add(productServiceIntegration.getProductById(productId)));
    }

    public void clearCart(String uuid) {
        execute(uuid, Cart::clearCart);
    }

    public void deleteFromCart(String uuid, Long productId) {
        execute(uuid, cart -> cart.delete(productId));
    }

    public void changeQuantity(String uuid, Long productId, Integer delta) {
        execute(uuid, cart -> cart.changeQuantity(productId,delta));
    }

    private void execute(String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);
    }

    public void merge(String userUuid, String guestCartUuid) {
        Cart guestCart = getCurrentCart(guestCartUuid);
        Cart userCart = getCurrentCart(userUuid);
        userCart.mergeCarts(guestCart);
        updateCart(guestCartUuid,guestCart);
        updateCart(userUuid,userCart);
    }

    public void updateCart(String uuid, Cart cart) {
        redisTemplate.opsForValue().set(uuid,cart);
    }
}
