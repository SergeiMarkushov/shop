package ru.gb.shop.core.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.shop.api.ProductDto;
import ru.gb.shop.core.entities.Product;
import ru.gb.shop.core.repositories.ProductRepository;
import ru.gb.shop.core.services.ProductService;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void findProductById() {

        Product newProduct = productService.createNewProduct(
                new ProductDto(8L,
                        "Title",
                        new BigDecimal("100.4"),
                        "Food"));

        productRepository.save(newProduct);

        Product productByHttp = webTestClient.get()
                .uri("/api/v1/products/" + newProduct.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .returnResult()
                .getResponseBody();


        Assertions.assertEquals(newProduct.getId(), productByHttp.getId());
        Assertions.assertEquals(newProduct.getTitle(), productByHttp.getTitle());

    }
}