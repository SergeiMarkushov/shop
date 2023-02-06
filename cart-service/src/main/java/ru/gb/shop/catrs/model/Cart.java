package ru.gb.shop.catrs.model;

import lombok.Data;
import ru.gb.shop.api.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }


    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice()).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public void add(ProductDto product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void addItem(CartItem cartItem) {
        for (CartItem item : items) {
            if (cartItem.getProductId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(cartItem.getProductId(), cartItem.getProductTitle(), 1, cartItem.getPricePerProduct(), cartItem.getPrice()));
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
                    delete(productId);
                    recalculate();
                }
                item.changeQuantity(delta);
                recalculate();
            }
        }
    }

    public void clearCart() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    public void mergeCarts(Cart guestCart) {
        for (CartItem item : guestCart.getItems()) {
            items.add(item);
            guestCart.clearCart();
            recalculate();
        }
    }
}
