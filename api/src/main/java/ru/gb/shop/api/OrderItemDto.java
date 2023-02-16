package ru.gb.shop.api;


import java.math.BigDecimal;

public class OrderItemDto {

    private Long id;
    private String productTitle;
    private Long orderId;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    private OrderItemDto(Builder builder) {
        id = builder.id;
        productTitle = builder.productTitle;
        orderId = builder.orderId;
        quantity = builder.quantity;
        pricePerProduct = builder.pricePerProduct;
        price = builder.price;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(OrderItemDto copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.productTitle = copy.getProductTitle();
        builder.orderId = copy.getOrderId();
        builder.quantity = copy.getQuantity();
        builder.pricePerProduct = copy.getPricePerProduct();
        builder.price = copy.getPrice();
        return builder;
    }


    public Long getId() {
        return id;
    }
    public String getProductTitle() {
        return productTitle;
    }
    public Long getOrderId() {
        return orderId;
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
        private Long id;
        private String productTitle;
        private Long orderId;
        private int quantity;
        private BigDecimal pricePerProduct;
        private BigDecimal price;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withProductTitle(String val) {
            productTitle = val;
            return this;
        }

        public Builder withOrderId(Long val) {
            orderId = val;
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

        public OrderItemDto build() {
            return new OrderItemDto(this);
        }
    }
}
