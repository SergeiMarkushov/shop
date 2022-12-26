package ru.gb.shop.api;

import java.util.List;


public class CartDto {
    private List<CartItemDto> items;
    private int totalPrice;

    public List<CartItemDto> getItems() {
        return items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto(List<CartItemDto> items, int totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public CartDto() {
    }
}
