package ru.gb.shop.dto;

import lombok.Data;
import ru.gb.shop.entities.Product;

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


    public void add(Product product) { //TODO: 1:22 HOMEWORK
        CartItem newItem = new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
        boolean add = false;

        for (CartItem item : items) {
            if (item.getProductId().equals(newItem.getProductId())) {
                item.setQuantity(item.getQuantity() + 1);
                add = true;
                totalPrice += newItem.getPrice();
            }
        }
        if (!add) {
            items.add(newItem);
            totalPrice += newItem.getPrice();
        }
    }

    public void delete(Long productId) {
        for (CartItem item : items) {
            if(item.getProductId().equals(productId)) {
                totalPrice -= item.getQuantity()*item.getPrice();
                items.remove(item);
            }
        }
//        items.removeIf(item -> item.getProductId().equals(productId));
    }

    public void changeQuantity(Long productId, Integer delta) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                if (item.getQuantity() == 0) {
                    items.remove(item);
                    totalPrice -= item.getPrice();
                } else {
                    item.setQuantity(item.getQuantity() + delta);
                    if (delta > 0) {
                        totalPrice += item.getPrice();
                    } else {
                        totalPrice -= item.getPrice();
                    }

                }
            }
        }
    }

    public void clearCart() {
        items.clear();
        totalPrice = 0;
    }
}
