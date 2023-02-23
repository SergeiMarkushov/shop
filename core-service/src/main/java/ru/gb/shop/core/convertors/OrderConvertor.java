package ru.gb.shop.core.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.shop.api.OrderDto;
import ru.gb.shop.core.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConvertor {

    private final OrderItemConvertor orderItemConvertor;
    public OrderDto orderToDto(Order order) {
        return OrderDto.newBuilder()
                .withId(order.getId())
                .withAddress(order.getAddress())
                .withPhone(order.getPhone())
                .withTotalPrice(order.getTotalPrice())
                .withUsername(order.getUsername())
                .withItems(order.getItems()
                        .stream()
                        .map(orderItemConvertor::orderItemToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
