package ru.gb.shop.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.shop.core.entities.OrderItem;
import ru.gb.shop.core.repositories.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}


