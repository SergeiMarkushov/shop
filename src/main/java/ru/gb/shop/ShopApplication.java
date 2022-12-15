package ru.gb.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    // группировка продукта в корзине
    // добавить возможность по кнопке очищать корзину.
    // добавть возможность увеличивать. уменьшать количество товаров в корзине
    // добавить возможность удалять строку корзины.
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
