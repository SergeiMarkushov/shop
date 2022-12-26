package ru.gb.shop.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.shop.core.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
