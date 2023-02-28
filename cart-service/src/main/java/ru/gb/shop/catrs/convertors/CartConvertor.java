package ru.gb.shop.catrs.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.shop.api.CartDto;
import ru.gb.shop.catrs.model.Cart;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConvertor {
    private final CartItemConvertor cartItemConvertor;

    public CartDto entityToDto(Cart cart) {
        return CartDto.newBuilder()
                .withTotalPrice(cart.getTotalPrice())
                .withItems(cart.getItems().stream().map(cartItemConvertor::entityToDto).collect(Collectors.toList()))
                .build();

    }
}
