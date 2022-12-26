package ru.gb.shop.catrs.convertors;

import org.springframework.stereotype.Component;
import ru.gb.shop.api.CartItemDto;
import ru.gb.shop.catrs.model.CartItem;

@Component
public class CartItemConvertor {
    public CartItemDto entityToDto (CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        return cartItemDto;
    }
}
