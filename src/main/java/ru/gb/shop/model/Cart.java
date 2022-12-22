package ru.gb.shop.model;

import lombok.Data;
import ru.gb.shop.entities.ProductEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void add(ProductEntity productEntity) {
        for (CartItem item : items) {
            if (productEntity.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(productEntity.getId(), productEntity.getTitle(), 1, productEntity.getPrice(), productEntity.getPrice()));
        recalculate();
    }



    public void delete(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))){
            recalculate();
        }
    }

    public void changeQuantity(Long productId, Integer delta) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                if (item.getQuantity() == 0) {
                    items.remove(item);
                    recalculate();
                }
                item.changeQuantity(delta);
                recalculate();
            }
        }
    }

    public void clearCart() {
        items.clear();
        totalPrice = 0;
    }
}
