package ru.gb.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.shop.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
