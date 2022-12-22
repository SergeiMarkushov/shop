package ru.gb.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.shop.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {
}
