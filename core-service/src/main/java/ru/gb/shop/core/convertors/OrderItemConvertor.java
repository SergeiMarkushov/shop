package ru.gb.shop.core.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.shop.api.OrderItemDto;
import ru.gb.shop.core.entities.OrderItem;

@Component
@RequiredArgsConstructor
public class OrderItemConvertor {
    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        return OrderItemDto.newBuilder()
                .withId(orderItem.getId())
                .withPrice(orderItem.getPrice())
                .withQuantity(orderItem.getQuantity())
                .withPricePerProduct(orderItem.getPricePerProduct())
                .withProductTitle(orderItem.getProduct().getTitle())
                .build();
    }
}
