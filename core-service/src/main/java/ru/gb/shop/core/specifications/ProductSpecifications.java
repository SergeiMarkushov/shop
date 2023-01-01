package ru.gb.shop.core.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.shop.core.entities.Product;

public class ProductSpecifications {
    public static Specification<Product> costGreaterOrEqualsThan(Integer minCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost);
    }
    public static Specification<Product> costLessOrEqualsThan(Integer maxCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
    }
    public static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}