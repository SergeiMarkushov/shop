package ru.gb.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    /*
    Реализовать оформление заказа
    1. На фронте добавитьт в отделе корзины кнопку оформить заказ
    1.а.* С фронта передать адрес и телефон покупатель
    2. Заказ может оформлять только вошедший пользователь (защита)
    3. В ОрдерСервисе получите текущую корзину и преобразуйте ее к заказу
    4. Сохраните заказ с позициями заказа в бд
    5.** На фронте показать список заказов юзера
     */
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
