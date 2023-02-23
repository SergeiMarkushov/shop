package ru.gb.shop.api;

import java.math.BigDecimal;
import java.util.List;


public class CartDto {
    private List<CartItemDto> items;
    private BigDecimal totalPrice;

    private CartDto(Builder builder) {
        items = builder.items;
        totalPrice = builder.totalPrice;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static Builder newBuilder(CartDto copy) {
        Builder builder = new Builder();
        builder.items = copy.getItems();
        builder.totalPrice = copy.getTotalPrice();
        return builder;
    }

    public List<CartItemDto> getItems() {
        return items;
    }



    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public static final class Builder {
        private List<CartItemDto> items;
        private BigDecimal totalPrice;

        private Builder() {
        }



        public Builder withItems(List<CartItemDto> val) {
            items = val;
            return this;
        }

        public Builder withTotalPrice(BigDecimal val) {
            totalPrice = val;
            return this;
        }

        public CartDto build() {
            return new CartDto(this);
        }
    }
}
