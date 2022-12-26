package ru.gb.shop.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.shop.api.CartDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<CartDto> getCart() {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8190/shop-carts/api/v1/cart/", CartDto.class));
    }

    public void clear() {
        restTemplate.getForObject("http://localhost:8190/shop-carts/api/v1/cart/clear", CartDto.class);
    }

}