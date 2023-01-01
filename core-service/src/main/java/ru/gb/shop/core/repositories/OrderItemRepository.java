package ru.gb.shop.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.shop.core.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {
}
