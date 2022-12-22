package ru.gb.shop.soap;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.shop.entities.ProductEntity;

public interface ProductSOAPRepository extends JpaRepository<ProductEntity, Long> {
}
