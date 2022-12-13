package ru.gb.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.shop.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
