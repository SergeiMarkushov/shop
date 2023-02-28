package ru.gb.shop.api;


import java.math.BigDecimal;
import java.util.List;


public class OrderDto {
    private Long id;

    private String username;
    private List<OrderItemDto> items;
    private String address;
    private String phone;
    private BigDecimal totalPrice;

    private OrderDto(Builder builder) {
        id = builder.id;
        username = builder.username;
        items = builder.items;
        address = builder.address;
        phone = builder.phone;
        totalPrice = builder.totalPrice;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static Builder newBuilder(OrderDto copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.username = copy.getUsername();
        builder.items = copy.getItems();
        builder.address = copy.getAddress();
        builder.phone = copy.getPhone();
        builder.totalPrice = copy.getTotalPrice();
        return builder;
    }


    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public List<OrderItemDto> getItems() {
        return items;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public static final class Builder {
        private Long id;
        private String username;
        private List<OrderItemDto> items;
        private String address;
        private String phone;
        private BigDecimal totalPrice;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withItems(List<OrderItemDto> val) {
            items = val;
            return this;
        }

        public Builder withAddress(String val) {
            address = val;
            return this;
        }

        public Builder withPhone(String val) {
            phone = val;
            return this;
        }

        public Builder withTotalPrice(BigDecimal val) {
            totalPrice = val;
            return this;
        }

        public OrderDto build() {
            return new OrderDto(this);
        }
    }
}
