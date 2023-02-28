package ru.gb.shop.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gb.shop.api.ProductDto;
import ru.gb.shop.core.entities.Product;
import ru.gb.shop.core.repositories.ProductRepository;

import java.math.BigDecimal;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;


    @Test
    void createNewProduct() {
        Product product = productService.createNewProduct(
                new ProductDto(
                        1000L,
                        "Title",
                        new BigDecimal("100.4"),
                        "Food"));

        Assertions.assertNotNull(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals("Title", product.getTitle());

        Product product1 = productRepository.findById(product.getId()).orElse(null);
        Assertions.assertEquals("Title", product1.getTitle());
    }
}