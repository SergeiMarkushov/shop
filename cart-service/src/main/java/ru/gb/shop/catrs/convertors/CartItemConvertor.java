package ru.gb.shop.catrs.convertors;

import org.springframework.stereotype.Component;
import ru.gb.shop.api.CartItemDto;
import ru.gb.shop.catrs.model.CartItem;

@Component
public class CartItemConvertor {
    public CartItemDto entityToDto (CartItem cartItem) {
        return CartItemDto.newBuilder()
                .withPrice(cartItem.getPrice())
                .withPricePerProduct(cartItem.getPricePerProduct())
                .withProductId(cartItem.getProductId())
                .withProductTitle(cartItem.getProductTitle())
                .withQuantity(cartItem.getQuantity())
                .build();
    }
}
