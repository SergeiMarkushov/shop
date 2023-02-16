package ru.gb.shop.api;

import java.math.BigDecimal;

public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    private CartItemDto(Builder builder) {
        productId = builder.productId;
        productTitle = builder.productTitle;
        quantity = builder.quantity;
        pricePerProduct = builder.pricePerProduct;
        price = builder.price;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static Builder newBuilder(CartItemDto copy) {
        Builder builder = new Builder();
        builder.productId = copy.getProductId();
        builder.productTitle = copy.getProductTitle();
        builder.quantity = copy.getQuantity();
        builder.pricePerProduct = copy.getPricePerProduct();
        builder.price = copy.getPrice();
        return builder;
    }

    private String getProductTitle() {
        return productTitle;
    }


    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }



    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }



    public BigDecimal getPrice() {
        return price;
    }


    public static final class Builder {
        private Long productId;
        private String productTitle;
        private int quantity;
        private BigDecimal pricePerProduct;
        private BigDecimal price;

        private Builder() {
        }



        public Builder withProductId(Long val) {
            productId = val;
            return this;
        }

        public Builder withProductTitle(String val) {
            productTitle = val;
            return this;
        }

        public Builder withQuantity(int val) {
            quantity = val;
            return this;
        }

        public Builder withPricePerProduct(BigDecimal val) {
            pricePerProduct = val;
            return this;
        }

        public Builder withPrice(BigDecimal val) {
            price = val;
            return this;
        }

        public CartItemDto build() {
            return new CartItemDto(this);
        }
    }
}
