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
        System.err.println(cartItem.toString());
        ProductDto productDto = new ProductDto();
        productDto.setId(cartItem.getProductId());
        productDto.setPrice(cartItem.getPrice());
        productDto.setTitle(cartItem.getProductTitle());
        add(productDto);
        changeQuantity(productDto.getId(), cartItem.getQuantity() - 1);
    }

    public void delete(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))){
            recalculate();
        }
    }

    public void changeQuantity(Long productId, Integer delta) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.changeQuantity(delta);
                if (item.getQuantity() == 0) {
                    delete(productId);
                    return;
                }
                recalculate();
                return;
            }
        }
    }

    public void clearCart() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }
}
